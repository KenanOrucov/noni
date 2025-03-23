package com.nonilab.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionResponse {
    private Long id;
    private String value;
    private String label;
    private Boolean isVisible;
}
