package com.nonilab.controller;

import com.nonilab.model.request.ElementRequest;
import com.nonilab.model.request.ElementRequestForUpdate;
import com.nonilab.model.request.ElementSearchRequest;
import com.nonilab.model.response.ElementResponse;
import com.nonilab.service.abstraction.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elements")
@RequiredArgsConstructor
public class ElementController {
    private final ElementService elementService;

//    @GetMapping
//    public List<ElementResponse> getAllElements() {
//        return elementService.getAllElements();
//    }

    @GetMapping("/fields")
    public List<ElementResponse> searchElements(@RequestParam(required = false) String elementType,
                                                @RequestParam(required = false) String elementName,
                                                @RequestParam(required = false) String screenName,
                                                @RequestParam(required = false) String language) {
        return elementService.searchElements(elementType, elementName, screenName, language);
    }

    @GetMapping("/{id}")
    public ElementResponse getElementById(@PathVariable Long id) {
        return elementService.getElementById(id);
    }

    @PostMapping
    public void saveElement(@RequestBody ElementRequest elementRequest) {
        elementService.createElement(elementRequest);
    }

    @PutMapping("/{id}")
    public void updateElement(@PathVariable Long id, @RequestBody ElementRequestForUpdate elementRequest) {
        elementService.updateElement(id, elementRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteElement(@PathVariable Long id) {
        elementService.deleteElement(id);
    }
}
