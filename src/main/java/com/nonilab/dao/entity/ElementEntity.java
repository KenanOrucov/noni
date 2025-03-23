package com.nonilab.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
@Entity
@Table(name = "elements")
@ToString
public class ElementEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String elementType;
    private String elementName;
    private String screenName;
    private String language;
    private Boolean isVisible;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "properties_id")
    private PropertyEntity propertyEntity;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementEntity that = (ElementEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
