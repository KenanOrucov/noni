package com.nonilab.dao.repository;

import com.nonilab.dao.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
}
