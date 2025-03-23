package com.nonilab.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StyleResponse {
    private Long id;
    private String fontSize;
    private String fontColor;
    private String padding;
    private String margin;
    private Boolean isVisible;
}
