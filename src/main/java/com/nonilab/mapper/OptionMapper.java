package com.nonilab.mapper;

import com.nonilab.dao.entity.OptionEntity;
import com.nonilab.dao.entity.PropertyEntity;
import com.nonilab.dao.entity.ValidationRuleEntity;
import com.nonilab.model.request.OptionRequest;
import com.nonilab.model.request.OptionRequestForUpdate;
import com.nonilab.model.request.ValidationRequest;
import com.nonilab.model.response.OptionResponse;
import com.nonilab.model.response.ValidationResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public enum OptionMapper {
    OPTION_MAPPER;

    public OptionResponse toOptionResponse(OptionEntity optionEntity) {
        return OptionResponse.builder()
                .id(optionEntity.getId())
                .value(optionEntity.getValue())
                .label(optionEntity.getLabel())
                .isVisible(optionEntity.getIsVisible())
                .build();
    }

    public OptionEntity toOptionEntity(PropertyEntity propertyEntity, OptionRequest optionRequest) {
        return OptionEntity.builder()
                .value(optionRequest.getValue())
                .label(optionRequest.getLabel())
                .isVisible(optionRequest.getIsVisible())
                .propertyEntity(propertyEntity)
                .build();
    }

    public List<OptionResponse> toOptionResponses(List<OptionEntity> optionEntities) {
        return optionEntities
                .stream()
                .map(this::toOptionResponse)
                .toList();
    }

    public void updateOptions(List<OptionEntity> optionEntities, List<OptionRequestForUpdate> optionRequests) {
       optionEntities
               .forEach(o -> this.updateOption(o, optionRequests));
    }

    private void updateOption(OptionEntity optionEntity, List<OptionRequestForUpdate> optionRequest) {
        for (OptionRequestForUpdate optionRequestEntity : optionRequest) {
            log.info("optionRequestEntity.getId() {}", optionRequestEntity.getId());
            log.info("optionEntity.getId() {}", optionEntity.getId());
            if (optionRequestEntity.getId().equals(optionEntity.getId())) {
                optionEntity.setValue(optionRequestEntity.getValue());
                optionEntity.setLabel(optionRequestEntity.getLabel());
                optionEntity.setIsVisible(optionRequestEntity.getIsVisible());
            }
        }
    }

}
