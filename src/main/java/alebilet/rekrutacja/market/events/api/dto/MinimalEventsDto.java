package alebilet.rekrutacja.market.events.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MinimalEventsDto(List<MinimalEventDto> events) {

    public record MinimalEventDto(UUID publicId,
                                  String name,
                                  LocalDateTime date,
                                  String location,
                                  String description,
                                  String imageUrl,
                                  BigDecimal ticketLowestPrice){}
}
