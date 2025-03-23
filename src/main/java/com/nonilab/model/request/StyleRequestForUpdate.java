package com.nonilab.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StyleRequestForUpdate {
    private Long id;
    private String fontSize;
    private String fontColor;
    private String padding;
    private String margin;
    private Boolean isVisible;
}
