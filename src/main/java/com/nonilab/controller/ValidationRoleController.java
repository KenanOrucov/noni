package com.nonilab.controller;

import com.nonilab.service.abstraction.StyleService;
import com.nonilab.service.abstraction.ValidationRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validations")
@RequiredArgsConstructor
public class ValidationRoleController {

    private final ValidationRuleService validationRuleService;

    @DeleteMapping("/{id}")
    public void deleteValidation(@PathVariable Long id) {
        validationRuleService.deleteValidation(id);
    }
}
