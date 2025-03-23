package com.nonilab.controller;

import com.nonilab.service.abstraction.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/styles")
@RequiredArgsConstructor
public class StyleController {

    private final StyleService styleService;

    @DeleteMapping("/{id}")
    public void deleteStyle(@PathVariable Long id) {
        styleService.deleteStyle(id);
    }
}
