package com.nonilab.mapper;

import com.nonilab.dao.entity.ElementEntity;
import com.nonilab.dao.entity.PropertyEntity;
import com.nonilab.model.request.ElementRequest;
import com.nonilab.model.request.ElementRequestForUpdate;
import com.nonilab.model.response.ElementResponse;

import java.util.List;

import static com.nonilab.mapper.PropertyMapper.PROPERTY_MAPPER;

public enum ElementMapper {
    ELEMENT_MAPPER;

    public ElementResponse toElementResponse(ElementEntity element) {
        var elementResponse = ElementResponse.builder()
                .id(element.getId())
                .elementType(element.getElementType())
                .elementName(element.getElementName())
                .language(element.getLanguage())
                .screenName(element.getScreenName())
                .isVisible(element.getIsVisible())
                .build();

        if (element.getPropertyEntity() != null) {
            elementResponse.setProperty(PROPERTY_MAPPER.toPropertyResponse(element.getPropertyEntity()));
        }

        return elementResponse;
    }

    public ElementEntity toElementEntity(ElementRequest element) {
        return ElementEntity.builder()
                .elementType(element.getElementType())
                .elementName(element.getElementName())
                .language(element.getLanguage())
                .screenName(element.getScreenName())
                .isVisible(element.getIsVisible())
//                .propertyEntity(PROPERTY_MAPPER.toPropertyEntity(element.getProperty()))
                .build();
    }

    public void updateElementEntity(ElementEntity element, ElementRequestForUpdate elementRequest) {
        element.setElementType(elementRequest.getElementType());
        element.setElementName(elementRequest.getElementName());
        element.setLanguage(elementRequest.getLanguage());
        element.setIsVisible(elementRequest.getIsVisible());
        element.setScreenName(elementRequest.getScreenName());

    }


    public void setPropertyToElement(ElementEntity elementEntity, PropertyEntity propertyEntity) {
        elementEntity.setPropertyEntity(propertyEntity);
    }

    public List<ElementResponse> toElementResponses(List<ElementEntity> entities) {
        return entities
                .stream()
                .map(this::toElementResponse)
                .toList();
    }
}
