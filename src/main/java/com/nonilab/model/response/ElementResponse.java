package com.nonilab.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElementResponse {
    private Long id;
    private String elementType;
    private String elementName;
    private String language;
    private String screenName;
    private Boolean isVisible;
    private PropertyResponse property;

}
