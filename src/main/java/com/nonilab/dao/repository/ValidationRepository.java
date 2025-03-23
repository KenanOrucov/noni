package com.nonilab.dao.repository;

import com.nonilab.dao.entity.ValidationRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationRepository extends JpaRepository<ValidationRuleEntity, Long> {
}
