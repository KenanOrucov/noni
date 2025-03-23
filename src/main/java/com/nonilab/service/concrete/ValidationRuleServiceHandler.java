package com.nonilab.service.concrete;

import com.nonilab.dao.entity.ValidationRuleEntity;
import com.nonilab.dao.repository.ValidationRepository;
import com.nonilab.exception.NotFoundException;
import com.nonilab.service.abstraction.ValidationRuleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.nonilab.model.enums.ExceptionMessages.VALIDATION_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationRuleServiceHandler implements ValidationRuleService {

    private final ValidationRepository validationRepository;

    @Override
    @Transactional
    public void deleteValidation(Long id) {
        log.info("ActionLog.ValidationRuleServiceHandler.deleteValidation start: {}", id);
        var validationRule = fetchValidationIfExists(id);
        validationRule.getPropertyEntity().getOptionEntities().remove(null);
        validationRepository.delete(validationRule);
    }

    private ValidationRuleEntity fetchValidationIfExists(Long id) {
        return validationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(VALIDATION_NOT_FOUND.getMessage(), VALIDATION_NOT_FOUND.getCode()));
    }
}
