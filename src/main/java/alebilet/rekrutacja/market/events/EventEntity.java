package alebilet.rekrutacja.market.events;

import alebilet.rekrutacja.market.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Getter
@Setter
class EventEntity extends BaseEntity {
    private String name;
    private LocalDateTime date;
    private String location;
    private String description;
    private String imageUrl;
    @OneToMany(mappedBy = "event")
    private Set<TicketEntity> tickets;
}
