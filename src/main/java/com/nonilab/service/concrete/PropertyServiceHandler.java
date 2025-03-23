package com.nonilab.service.concrete;

import com.nonilab.dao.entity.PropertyEntity;
import com.nonilab.dao.repository.PropertyRepository;
import com.nonilab.exception.NotFoundException;
import com.nonilab.service.abstraction.PropertyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.nonilab.model.enums.ExceptionMessages.PROPERTY_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyServiceHandler implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        log.info("ActionLog.PropertyServiceHandler.deleteProperty start: {}", id);
        var property = fetchPropertyIfExists(id);
        property.getElementEntity().setPropertyEntity(null);
        propertyRepository.delete(property);
    }

    private PropertyEntity fetchPropertyIfExists(Long id) {
        return propertyRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(PROPERTY_NOT_FOUND.getMessage(), PROPERTY_NOT_FOUND.getCode()));
    }
}
