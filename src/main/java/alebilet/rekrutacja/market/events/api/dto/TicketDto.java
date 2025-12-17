package alebilet.rekrutacja.market.events.api.dto;

import alebilet.rekrutacja.market.events.api.enums.TicketCategory;
import alebilet.rekrutacja.market.events.api.enums.TicketStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record TicketDto(UUID publicId,
                        BigDecimal price,
                        BigDecimal originalPrice,
                        String ticketNumber,
                        TicketStatus status,
                        TicketCategory category){}
