package alebilet.rekrutacja.market.events.api;

import alebilet.rekrutacja.market.events.api.commands.AddTicketCommand;
import alebilet.rekrutacja.market.events.api.dto.DetailedEventDto;
import alebilet.rekrutacja.market.events.api.dto.MinimalEventsDto;

import java.util.UUID;

public interface EventFacade {

    MinimalEventsDto getUpcomingEvents();
    DetailedEventDto getEventDetails(UUID publicEventId);
    void addTicket(UUID publicEventId, AddTicketCommand command);

}
