package alebilet.rekrutacja.market.events;

import alebilet.rekrutacja.market.NotFoundException;
import alebilet.rekrutacja.market.events.api.EventFacade;
import alebilet.rekrutacja.market.events.api.commands.AddTicketCommand;
import alebilet.rekrutacja.market.events.api.dto.DetailedEventDto;
import alebilet.rekrutacja.market.events.api.dto.MinimalEventsDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class EventService implements EventFacade {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final EventMapper eventMapper;

    @Override
    public MinimalEventsDto getUpcomingEvents() {
        var events = eventRepository.findUpcomingEventsWithLowestTicketPrice(LocalDateTime.now());
        var mappedEvents = events.stream().map(eventMapper::toDto).toList();
        return new MinimalEventsDto(mappedEvents);
    }

    @Override
    public DetailedEventDto getEventDetails(UUID publicEventId) {
        var event = eventRepository.findWithTicketsByPublicId(publicEventId)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + publicEventId));

        return eventMapper.toDetailedDto(event);
    }

    @Override
    @Transactional
    public void addTicket(UUID publicEventId, AddTicketCommand command) {
        log.info("Adding ticket for event: {} with command: {}", publicEventId, command);
        var eventEntity = eventRepository.findByPublicId(publicEventId)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + publicEventId));
        var ticketToSave = new TicketEntity(
                command.price(),
                command.originalPrice(),
                command.ticketNumber(),
                TicketStatus.AVAILABLE,
                command.category(),
                eventEntity
        );
        ticketRepository.save(ticketToSave);
    }
}
