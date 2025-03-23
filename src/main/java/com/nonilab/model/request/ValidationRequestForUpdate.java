package com.nonilab.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationRequestForUpdate {
    private Long id;
    private String value;
    private String ruleType;
    private Boolean isVisible;
}
