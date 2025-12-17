package alebilet.rekrutacja.market.events;

import alebilet.rekrutacja.market.BaseEntity;
import alebilet.rekrutacja.market.events.api.enums.TicketCategory;
import alebilet.rekrutacja.market.events.api.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class TicketEntity extends BaseEntity {
    private BigDecimal price;
    private BigDecimal originalPrice;
    private String ticketNumber;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    private TicketCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;
}
