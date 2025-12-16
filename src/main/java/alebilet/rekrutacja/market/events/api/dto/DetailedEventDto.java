package alebilet.rekrutacja.market.events.api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DetailedEventDto(UUID publicId,
                               String name,
                               LocalDateTime date,
                               String location,
                               String description,
                               String imageUrl,
                               List<TicketDto> tickets) {
}
