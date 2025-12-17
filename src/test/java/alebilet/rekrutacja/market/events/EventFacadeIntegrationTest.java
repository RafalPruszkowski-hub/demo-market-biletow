package alebilet.rekrutacja.market.events;

import alebilet.rekrutacja.market.NotFoundException;
import alebilet.rekrutacja.market.events.api.EventFacade;
import alebilet.rekrutacja.market.events.api.enums.TicketCategory;
import alebilet.rekrutacja.market.events.api.enums.TicketStatus;
import alebilet.rekrutacja.market.events.api.commands.AddTicketCommand;
import alebilet.rekrutacja.market.events.api.dto.DetailedEventDto;
import alebilet.rekrutacja.market.events.api.dto.MinimalEventsDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb-integration",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.defer-datasource-initialization=true"
})
@Transactional
@DisplayName("Event Facade Integration Tests")
class EventFacadeIntegrationTest {

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // UUID from data.sql - Future events
    private static final UUID MARATHON_ID = UUID.fromString("550e8400-e29b-41d4-a716-446655440009");
    private static final UUID STARTUP_SUMMIT_ID = UUID.fromString("550e8400-e29b-41d4-a716-446655440007");
    private static final UUID CLASSICAL_MUSIC_ID = UUID.fromString("550e8400-e29b-41d4-a716-446655440003");

    // UUID from data.sql - Past events
    private static final UUID PAST_ROCK_CONCERT_ID = UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
    private static final UUID PAST_TECH_SUMMIT_ID = UUID.fromString("550e8400-e29b-41d4-a716-446655440002");

    @Nested
    @DisplayName("Get Upcoming Events")
    class GetUpcomingEventsTests {

        @Test
        @DisplayName("Should return only future events")
        void shouldReturnOnlyFutureEvents() {
            // given - data.sql contains 9 future events and 2 past events

            // when
            MinimalEventsDto result = eventFacade.getUpcomingEvents();

            // then
            assertThat(result).isNotNull();
            assertThat(result.events()).isNotEmpty();
            assertThat(result.events()).extracting(MinimalEventsDto.MinimalEventDto::name).contains("Marathon 2025", "Startup Summit", "Classical Music Night").doesNotContain("Rock Festival 2023", "Tech Conference");
        }

        @Test
        @DisplayName("Should not return past events")
        void shouldNotReturnPastEvents() {
            // given - data.sql contains 2 past events (Past Rock Concert, Past Tech Summit)

            // when
            MinimalEventsDto result = eventFacade.getUpcomingEvents();

            // then
            assertThat(result).isNotNull();
            assertThat(result.events()).extracting(MinimalEventsDto.MinimalEventDto::name).doesNotContain("Past Rock Concert", "Past Tech Summit");

            // verify past events exist in database but are not returned
            assertThat(eventRepository.findByPublicId(PAST_ROCK_CONCERT_ID)).isPresent();
            assertThat(eventRepository.findByPublicId(PAST_TECH_SUMMIT_ID)).isPresent();
        }

        @Test
        @DisplayName("Should return empty list when no events exist")
        void shouldReturnEmptyListWhenNoEventsExist() {
            // given - delete all events
            ticketRepository.deleteAll();
            eventRepository.deleteAll();

            // when
            MinimalEventsDto result = eventFacade.getUpcomingEvents();

            // then
            assertThat(result).isNotNull();
            assertThat(result.events()).isEmpty();
        }

        @Test
        @DisplayName("Should return lowest ticket price for each event")
        void shouldReturnLowestTicketPrice() {
            // given - Marathon 2025 has tickets: 39.99, 49.99, 59.99, 69.99, 79.99 from data.sql

            // when
            MinimalEventsDto result = eventFacade.getUpcomingEvents();

            // then
            var marathon = result.events().stream().filter(e -> e.name().equals("Marathon 2025")).findFirst();

            assertThat(marathon).isPresent();
            assertThat(marathon.get().ticketLowestPrice()).isEqualByComparingTo(new BigDecimal("39.99"));
        }
    }

    @Nested
    @DisplayName("Get Event Details")
    class GetEventDetailsTests {

        @Test
        @DisplayName("Should return event with tickets when event exists")
        void shouldReturnEventWithTicketsWhenEventExists() {
            // given - Marathon 2025 from data.sql with 5 tickets

            // when
            DetailedEventDto result = eventFacade.getEventDetails(MARATHON_ID);

            // then
            assertThat(result).isNotNull();
            assertThat(result.name()).isEqualTo("Marathon 2025");
            assertThat(result.location()).isEqualTo("Warsaw, City Center");
            assertThat(result.tickets()).hasSize(5);
        }

        @Test
        @DisplayName("Should throw NotFoundException when event does not exist")
        void shouldThrowNotFoundExceptionWhenEventDoesNotExist() {
            // given
            UUID nonExistentId = UUID.randomUUID();

            // when & then
            assertThatThrownBy(() -> eventFacade.getEventDetails(nonExistentId)).isInstanceOf(NotFoundException.class).hasMessageContaining("Event not found with id: " + nonExistentId);
        }
    }

    @Nested
    @DisplayName("Add Ticket")
    class AddTicketTests {

        @Test
        @DisplayName("Should save ticket when event exists")
        void shouldSaveTicketWhenEventExists() {
            // given - Marathon 2025 from data.sql (already has 5 tickets)
            AddTicketCommand command = new AddTicketCommand(new BigDecimal("89.99"), new BigDecimal("99.99"), "TKT-TEST-001", TicketCategory.STANDARD);

            // when
            eventFacade.addTicket(MARATHON_ID, command);

            // then
            var eventTickets = ticketRepository.findAll().stream().filter(t -> t.getEvent().getPublicId().equals(MARATHON_ID)).toList();

            assertThat(eventTickets).hasSizeGreaterThan(5); // original 5 + 1 new
            assertThat(eventTickets).anyMatch(t -> t.getPrice().compareTo(new BigDecimal("89.99")) == 0 && t.getTicketNumber().equals("TKT-TEST-001") && t.getCategory() == TicketCategory.STANDARD);
        }

        @Test
        @DisplayName("Should throw NotFoundException when event does not exist")
        void shouldThrowNotFoundExceptionWhenEventDoesNotExist() {
            // given
            UUID nonExistentId = UUID.randomUUID();
            AddTicketCommand command = new AddTicketCommand(new BigDecimal("199.99"), new BigDecimal("249.99"), "TKT-TEST-002", TicketCategory.STANDARD);
            int initialTicketCount = ticketRepository.findAll().size();

            // when & then
            assertThatThrownBy(() -> eventFacade.addTicket(nonExistentId, command)).isInstanceOf(NotFoundException.class).hasMessageContaining("Event not found with id: " + nonExistentId);

            assertThat(ticketRepository.findAll()).hasSize(initialTicketCount); // no new tickets added
        }

        @Test
        @DisplayName("Should create ticket with AVAILABLE status")
        void shouldCreateTicketWithAvailableStatus() {
            // given - Startup Summit from data.sql
            AddTicketCommand command = new AddTicketCommand(new BigDecimal("399.99"), new BigDecimal("449.99"), "TKT-TEST-003", TicketCategory.PREMIUM);

            // when
            eventFacade.addTicket(STARTUP_SUMMIT_ID, command);

            // then
            var newTickets = ticketRepository.findAll().stream().filter(t -> t.getEvent().getPublicId().equals(STARTUP_SUMMIT_ID)).filter(t -> t.getTicketNumber().equals("TKT-TEST-003")).toList();

            assertThat(newTickets).isNotEmpty();
            assertThat(newTickets.get(0).getStatus()).isEqualTo(TicketStatus.AVAILABLE);
        }

        @Test
        @DisplayName("Should allow multiple tickets for same event")
        void shouldAllowMultipleTicketsForSameEvent() {
            // given - Classical Music event from data.sql
            AddTicketCommand command1 = new AddTicketCommand(new BigDecimal("299.99"), new BigDecimal("349.99"), "TKT-TEST-004", TicketCategory.PREMIUM);
            AddTicketCommand command2 = new AddTicketCommand(new BigDecimal("399.99"), new BigDecimal("449.99"), "TKT-TEST-005", TicketCategory.PREMIUM);
            int initialTicketCount = ticketRepository.findAll().stream().filter(t -> t.getEvent().getPublicId().equals(CLASSICAL_MUSIC_ID)).toList().size();

            // when
            eventFacade.addTicket(CLASSICAL_MUSIC_ID, command1);
            eventFacade.addTicket(CLASSICAL_MUSIC_ID, command2);

            // then
            var eventTickets = ticketRepository.findAll().stream().filter(t -> t.getEvent().getPublicId().equals(CLASSICAL_MUSIC_ID)).toList();

            assertThat(eventTickets).hasSize(initialTicketCount + 2);
            assertThat(eventTickets).extracting(TicketEntity::getPrice).contains(new BigDecimal("299.99"), new BigDecimal("399.99"));
        }
    }
}
