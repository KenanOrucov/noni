package com.nonilab.service.abstraction;

import com.nonilab.model.request.ElementRequest;
import com.nonilab.model.request.ElementRequestForUpdate;
import com.nonilab.model.response.ElementResponse;

import java.util.List;

public interface ElementService {
//    List<ElementResponse> getAllElements();
    ElementResponse getElementById(Long id);
    void createElement(ElementRequest elementRequest);
    void updateElement(Long id, ElementRequestForUpdate elementRequest);
    void deleteElement(Long id);
    List<ElementResponse> searchElements(String elementType, String elementName, String screenName, String language);

}
