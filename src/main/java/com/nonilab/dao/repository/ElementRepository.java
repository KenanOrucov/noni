package com.nonilab.dao.repository;

import com.nonilab.dao.entity.ElementEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ElementRepository extends JpaRepository<ElementEntity, Long> {
    @EntityGraph(attributePaths = {
            "propertyEntity",
            "propertyEntity.styleEntity",
            "propertyEntity.validationRuleEntities",
            "propertyEntity.optionEntities"
    })
    Optional<ElementEntity> findById(Long id);

    @EntityGraph(attributePaths = {
            "propertyEntity",
            "propertyEntity.styleEntity",
            "propertyEntity.validationRuleEntities",
            "propertyEntity.optionEntities"
    })
    List<ElementEntity> findAllElements();



    @Query("SELECT e FROM ElementEntity e WHERE " +
            "(:elementType IS NULL OR e.elementType = :elementType) AND " +
            "(:elementName IS NULL OR e.elementName = :elementName) AND " +
            "(:screenName IS NULL OR e.screenName = :screenName) AND " +
            "(:language IS NULL OR e.language = :language)")
    List<ElementEntity> findByDynamicSearch(
            @Param("elementType") String elementType,
            @Param("elementName") String elementName,
            @Param("screenName") String screenName,
            @Param("language") String language
    );
}
