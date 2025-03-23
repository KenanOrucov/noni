package com.nonilab.dao.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
@Entity
@Table(name = "properties")
@ToString
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String placeholder;
    private String text;
    private String icon;
    private String borderColor;
    private String backgroundColor;
    private Boolean isRequired;
    private String defaultValue;
    private Boolean isDisabled;
    private Integer maxLength;
    private Integer minLength;
    private Boolean isVisible;

    @OneToOne(
            mappedBy = "propertyEntity",
            fetch = LAZY
    )
    private ElementEntity elementEntity;


    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "styles_id")
    private StyleEntity styleEntity;

    @OneToMany(
            mappedBy = "propertyEntity",
            cascade = {PERSIST, MERGE, REMOVE}
    )
    private List<ValidationRuleEntity> validationRuleEntities;

    @OneToMany(
            mappedBy = "propertyEntity",
            cascade = {PERSIST, MERGE, REMOVE}
    )
    private List<OptionEntity> optionEntities;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyEntity that = (PropertyEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
