package alebilet.rekrutacja.market.events;

import org.springframework.data.jpa.repository.JpaRepository;

interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
