package alebilet.rekrutacja.market.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

record EventWithLowestPriceProjection(Long id,
                                             UUID publicId,
                                             String name,
                                             LocalDateTime date,
                                             String location,
                                             String description,
                                             String imageUrl,
                                             BigDecimal ticketLowestPrice){

}

