package com.nonilab.mapper;

import com.nonilab.dao.entity.*;
import com.nonilab.model.request.PropertyRequest;
import com.nonilab.model.request.PropertyRequestForUpdate;
import com.nonilab.model.response.PropertyResponse;

import java.util.List;

import static com.nonilab.mapper.OptionMapper.OPTION_MAPPER;
import static com.nonilab.mapper.StyleMapper.STYLE_MAPPER;
import static com.nonilab.mapper.ValidationMapper.VALIDATION_MAPPER;

public enum PropertyMapper {
    PROPERTY_MAPPER;

    public PropertyResponse toPropertyResponse(PropertyEntity property) {
        return PropertyResponse.builder()
                .id(property.getId())
                .placeholder(property.getPlaceholder())
                .text(property.getText())
                .icon(property.getIcon())
                .borderColor(property.getBorderColor())
                .backgroundColor(property.getBackgroundColor())
                .isRequired(property.getIsRequired())
                .defaultValue(property.getDefaultValue())
                .isDisabled(property.getIsDisabled())
                .maxLength(property.getMaxLength())
                .minLength(property.getMinLength())
                .isVisible(property.getIsVisible())
                .style(STYLE_MAPPER.toStyleResponse(property.getStyleEntity()))
                .validationResponses(VALIDATION_MAPPER.toValidationResponses(property.getValidationRuleEntities()))
                .optionResponses(OPTION_MAPPER.toOptionResponses(property.getOptionEntities()))
                .build();
    }

    public PropertyEntity toPropertyEntity(PropertyRequest property, ElementEntity element) {
        return PropertyEntity.builder()
                .placeholder(property.getPlaceholder())
                .text(property.getText())
                .icon(property.getIcon())
                .borderColor(property.getBorderColor())
                .backgroundColor(property.getBackgroundColor())
                .isRequired(property.getIsRequired())
                .defaultValue(property.getDefaultValue())
                .isDisabled(property.getIsDisabled())
                .maxLength(property.getMaxLength())
                .minLength(property.getMinLength())
                .isVisible(property.getIsVisible())
                .elementEntity(element)
//                .styleEntity(STYLE_MAPPER.toStyleEntity(property.getStyle()))
//                .validationRuleEntities(VALIDATION_MAPPER.toValidationEntities(property.getValidationRequests()))
//                .optionEntities(OPTION_MAPPER.toOptionEntities(property.getOptionRequests()))
                .build();
    }

    public void setStyleToProperty(PropertyEntity propertyEntity, StyleEntity styleElement) {
        propertyEntity.setStyleEntity(styleElement);
    }

    public void setOptionToProperty(PropertyEntity propertyEntity, List<OptionEntity> optionEntity) {
        propertyEntity.setOptionEntities(optionEntity);
    }

    public void setValidationToProperty(PropertyEntity propertyEntity, List<ValidationRuleEntity> validationRuleEntities) {
        propertyEntity.setValidationRuleEntities(validationRuleEntities);
    }

    public void updatePropertyEntity(PropertyEntity property, PropertyRequestForUpdate propertyRequest) {
        property.setPlaceholder(propertyRequest.getPlaceholder());
        property.setText(propertyRequest.getText());
        property.setIcon(propertyRequest.getIcon());
        property.setBorderColor(propertyRequest.getBorderColor());
        property.setBackgroundColor(propertyRequest.getBackgroundColor());
        property.setIsRequired(propertyRequest.getIsRequired());
        property.setDefaultValue(propertyRequest.getDefaultValue());
        property.setIsDisabled(propertyRequest.getIsDisabled());
        property.setMaxLength(propertyRequest.getMaxLength());
        property.setMinLength(propertyRequest.getMinLength());
        property.setIsVisible(propertyRequest.getIsVisible());
    }
}
