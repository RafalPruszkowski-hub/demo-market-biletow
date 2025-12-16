package alebilet.rekrutacja.market.events;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("""
                SELECT new alebilet.rekrutacja.market.events.EventWithLowestPriceProjection(
                            e.id, e.publicId, e.name, e.date, e.location, e.description, e.imageUrl, MIN(t.price))
                FROM EventEntity e
                LEFT JOIN e.tickets t
                GROUP BY e.id, e.publicId, e.name, e.date, e.location, e.description, e.imageUrl
                HAVING e.date > :now
            """)
    //countQuery = "SELECT COUNT(*) FROM EventEntity", nativeQuery = true najprosciej gybysmy chcieli
        // zrobic pagowana strone
    Set<EventWithLowestPriceProjection> findUpcomingEventsWithLowestTicketPrice(@Param("now") LocalDateTime now);

    @EntityGraph(attributePaths = {"tickets"})
    Optional<EventEntity> findWithTicketsByPublicId(UUID publicId);

    Optional<EventEntity> findByPublicId(UUID publicId);
}
