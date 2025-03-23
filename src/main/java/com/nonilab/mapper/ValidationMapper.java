package com.nonilab.mapper;

import com.nonilab.dao.entity.OptionEntity;
import com.nonilab.dao.entity.PropertyEntity;
import com.nonilab.dao.entity.ValidationRuleEntity;
import com.nonilab.model.request.OptionRequest;
import com.nonilab.model.request.OptionRequestForUpdate;
import com.nonilab.model.request.ValidationRequest;
import com.nonilab.model.request.ValidationRequestForUpdate;
import com.nonilab.model.response.ValidationResponse;

import java.util.List;

public enum ValidationMapper {
    VALIDATION_MAPPER;

    public ValidationResponse toValidationResponse(ValidationRuleEntity validationRuleEntity) {
        return ValidationResponse.builder()
                .id(validationRuleEntity.getId())
                .value(validationRuleEntity.getValue())
                .ruleType(validationRuleEntity.getRuleType())
                .isVisible(validationRuleEntity.getIsVisible())
                .build();
    }

    public ValidationRuleEntity toValidationEntity(PropertyEntity propertyEntity, ValidationRequest validationRequest) {
        return ValidationRuleEntity.builder()
                .value(validationRequest.getValue())
                .ruleType(validationRequest.getRuleType())
                .isVisible(validationRequest.getIsVisible())
                .propertyEntity(propertyEntity)
                .build();
    }

    public List<ValidationResponse> toValidationResponses(List<ValidationRuleEntity> validationRuleEntities) {
        return validationRuleEntities
                .stream()
                .map(this::toValidationResponse)
                .toList();
    }

    public void updateValidations(List<ValidationRuleEntity> validationRule, List<ValidationRequestForUpdate> validationUpdates) {
        validationRule
                .forEach(o -> this.updateValidation(o, validationUpdates));
    }

    private void updateValidation(ValidationRuleEntity validationRule, List<ValidationRequestForUpdate> validationUpdates) {
        for (ValidationRequestForUpdate validationRequestForUpdate : validationUpdates) {
            if (validationRequestForUpdate.getId().equals(validationRule.getId())) {
                validationRule.setValue(validationRequestForUpdate.getValue());
                validationRule.setRuleType(validationRequestForUpdate.getRuleType());
                validationRule.setIsVisible(validationRequestForUpdate.getIsVisible());
            }
        }
    }

//    public List<ValidationRuleEntity> toValidationEntities(List<ValidationRequest> validationRequests) {
//        return validationRequests
//                .stream()
//                .map(this::toValidationEntity)
//                .toList();
//    }
}
