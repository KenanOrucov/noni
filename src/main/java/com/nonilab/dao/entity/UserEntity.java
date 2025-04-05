package com.nonilab.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nonilab.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@FieldDefaults(level = PRIVATE)
@NamedEntityGraph(
        name = "User.authorities",
        attributeNodes = @NamedAttributeNode("authorities")
)
@Where(clause = "user_status <> 'DELETED'")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String firstName;
    String lastName;
    String username;
    String mail;
    String password;
    String gender;
    String language;
    String country;
    Date birthday;
    Integer age;
    String profile_picture;
    Boolean isAccountBlocked;
    Boolean isAccountLocked;

    @Enumerated(STRING)
    @Column(name = "user_status")
    UserStatus userStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(
            fetch = LAZY,
            cascade = ALL,
            mappedBy = "user"
    )
    @ToString.Exclude
    @JsonBackReference
    Set<RoleEntity> authorities;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RoleEntity author = (RoleEntity) o;
        return getId() != null && Objects.equals(getId(), author.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @PrePersist
    private void prePersist() {
        this.isAccountBlocked = false;
        this.isAccountLocked = false;
    }
}
