package com.nonilab.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationResponse {
    private Long id;
    private String value;
    private String ruleType;
    private Boolean isVisible;
}
