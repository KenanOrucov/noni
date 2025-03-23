package com.nonilab.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionRequestForUpdate {
    private Long id;
    private String value;
    private String label;
    private Boolean isVisible;
}
