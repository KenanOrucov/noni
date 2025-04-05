package com.nonilab.mapper;

import com.nonilab.dao.entity.PropertyEntity;
import com.nonilab.dao.entity.StyleEntity;
import com.nonilab.model.request.StyleRequest;
import com.nonilab.model.request.StyleRequestForUpdate;
import com.nonilab.model.response.StyleResponse;


public enum StyleMapper {
    STYLE_MAPPER;

    public StyleEntity toStyleEntity(StyleRequest styleRequest, PropertyEntity propertyEntity) {
        return StyleEntity.builder()
                .fontSize(styleRequest.getFontSize())
                .fontColor(styleRequest.getFontColor())
                .padding(styleRequest.getPadding())
                .margin(styleRequest.getMargin())
                .isVisible(styleRequest.getIsVisible())
                .propertyEntity(propertyEntity)
                .build();
    }

    public StyleResponse toStyleResponse(StyleEntity styleEntity) {
        return StyleResponse.builder()
                .id(styleEntity.getId())
                .fontSize(styleEntity.getFontSize())
                .fontColor(styleEntity.getFontColor())
                .padding(styleEntity.getPadding())
                .margin(styleEntity.getMargin())
                .isVisible(styleEntity.getIsVisible())
                .build();
    }

    public void updateStyleEntity(StyleEntity styleEntity, StyleRequestForUpdate styleRequest) {
        styleEntity.setFontSize(styleRequest.getFontSize());
        styleEntity.setFontColor(styleRequest.getFontColor());
        styleEntity.setPadding(styleRequest.getPadding());
        styleEntity.setMargin(styleRequest.getMargin());
        styleEntity.setIsVisible(styleRequest.getIsVisible());
    }
}
