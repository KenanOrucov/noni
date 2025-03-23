package com.nonilab.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessages {
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred."),
    HTTP_METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "Method not allowed."),
    STYLE_NOT_FOUND("STYLE_NOT_FOUND", "Style not found."),
    ELEMENT_NOT_FOUND("ELEMENT_NOT_FOUND", "Element not found."),
    PROPERTY_NOT_FOUND("PROPERTY_NOT_FOUND", "Property not found."),
    OPTION_NOT_FOUND("OPTION_NOT_FOUND", "Option not found."),
    VALIDATION_NOT_FOUND("VALIDATION_NOT_FOUND", "Validation not found."),
    ACCESS_DENIED("ACCESS_DENIED", "Access denied.");
    private final String code;
    private final String message;
}
