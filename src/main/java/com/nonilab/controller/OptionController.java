package com.nonilab.controller;

import com.nonilab.service.abstraction.OptionService;
import com.nonilab.service.abstraction.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @DeleteMapping("/{id}")
    public void deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
    }
}
