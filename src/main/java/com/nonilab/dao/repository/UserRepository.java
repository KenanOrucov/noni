package com.nonilab.dao.repository;

import com.nonilab.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findByUsername(String email);

    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findByMail(String mail);

    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findById(Long id);
}
