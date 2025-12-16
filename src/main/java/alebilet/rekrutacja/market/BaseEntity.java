package alebilet.rekrutacja.market;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = -1L;

    @Column(name = "public_id", updatable = false, nullable = false, unique = true, columnDefinition = "uuid")
    private UUID publicId = UUID.randomUUID();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;

        BaseEntity that = (BaseEntity) o;
        return publicId != null && publicId.equals(that.publicId);
    }

    @Override
    public final int hashCode() {
        return publicId.hashCode();
    }
}
