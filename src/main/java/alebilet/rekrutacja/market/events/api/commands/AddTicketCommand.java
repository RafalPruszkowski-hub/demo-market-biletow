package alebilet.rekrutacja.market.events.api.commands;

import alebilet.rekrutacja.market.events.TicketCategory;

import java.math.BigDecimal;

public record AddTicketCommand(BigDecimal price,
                               BigDecimal originalPrice,
                               String ticketNumber,
                               TicketCategory category) {
    public AddTicketCommand {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (originalPrice == null || originalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Original price must be greater than 0");
        }
        if (ticketNumber == null || ticketNumber.isBlank()) {
            throw new IllegalArgumentException("Ticket number cannot be empty");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
    }
}
