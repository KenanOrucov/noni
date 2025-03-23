package com.nonilab.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
@Entity
@Table(name = "styles")
@ToString
public class StyleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String fontSize;
    private String fontColor;
    private String padding;
    private String margin;
    private Boolean isVisible;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(
            mappedBy = "styleEntity",
            fetch = LAZY
    )
    private PropertyEntity propertyEntity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StyleEntity that = (StyleEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
