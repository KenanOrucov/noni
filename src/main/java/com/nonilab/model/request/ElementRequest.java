package com.nonilab.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementRequest {
    private String elementType;
    private String elementName;
    private String language;
    private String screenName;
    private Boolean isVisible;
    private PropertyRequest property;

}
