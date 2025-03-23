package com.nonilab.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementRequestForUpdate {
    private String elementType;
    private String elementName;
    private String language;
    private String screenName;
    private Boolean isVisible;
    private PropertyRequestForUpdate property;

}
