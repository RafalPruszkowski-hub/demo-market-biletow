package alebilet.rekrutacja.market.events;

import alebilet.rekrutacja.market.events.api.dto.DetailedEventDto;
import alebilet.rekrutacja.market.events.api.dto.MinimalEventsDto;
import alebilet.rekrutacja.market.events.api.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
abstract class EventMapper {
    abstract MinimalEventsDto.MinimalEventDto toDto(EventWithLowestPriceProjection projection);

    @Mapping(target = "tickets", source = "event.tickets", qualifiedByName = "sortedTickets")
    abstract DetailedEventDto toDetailedDto(EventEntity event);

    @Named(value = "sortedTickets")
    public List<TicketDto> sortedTickets(Set<TicketEntity> value) {
        return value.stream().sorted(Comparator.comparing(TicketEntity::getPrice)).map(this::toDto).collect(Collectors.toList());
    }

    abstract TicketDto toDto(TicketEntity ticket);
}
