package com.nonilab.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private String placeholder;
    private String text;
    private String icon;
    private String borderColor;
    private String backgroundColor;
    private Boolean isRequired;
    private String defaultValue;
    private Boolean isDisabled;
    private Integer maxLength;
    private Integer minLength;
    private Boolean isVisible;
    private StyleRequest style;
    private List<ValidationRequest> validationRequests;
    private List<OptionRequest> optionRequests;
}
