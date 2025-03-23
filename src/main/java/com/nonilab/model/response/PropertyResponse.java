package com.nonilab.model.response;


import com.nonilab.model.request.OptionRequest;
import com.nonilab.model.request.StyleRequest;
import com.nonilab.model.request.ValidationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyResponse {
    private Long id;
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
    private StyleResponse style;
    private List<ValidationResponse> validationResponses;
    private List<OptionResponse> optionResponses;
}
