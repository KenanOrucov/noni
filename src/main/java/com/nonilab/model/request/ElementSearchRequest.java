package com.nonilab.model.request;

import lombok.Data;

@Data
public class ElementSearchRequest {
    private String elementType;
    private String elementName;
    private String screenName;
    private String language;
}
