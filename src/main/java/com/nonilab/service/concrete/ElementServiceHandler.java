package com.nonilab.service.concrete;

import com.nonilab.dao.entity.ElementEntity;
import com.nonilab.dao.entity.OptionEntity;
import com.nonilab.dao.entity.PropertyEntity;
import com.nonilab.dao.entity.ValidationRuleEntity;
import com.nonilab.dao.repository.ElementRepository;
import com.nonilab.dao.repository.PropertyRepository;
import com.nonilab.exception.NotFoundException;
import com.nonilab.mapper.ElementMapper;
import com.nonilab.mapper.StyleMapper;
import com.nonilab.model.request.ElementRequest;
import com.nonilab.model.request.ElementRequestForUpdate;
import com.nonilab.model.request.ElementSearchRequest;
import com.nonilab.model.response.ElementResponse;
import com.nonilab.service.abstraction.ElementService;
import com.nonilab.service.abstraction.PropertyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nonilab.mapper.ElementMapper.ELEMENT_MAPPER;
import static com.nonilab.mapper.OptionMapper.OPTION_MAPPER;
import static com.nonilab.mapper.PropertyMapper.PROPERTY_MAPPER;
import static com.nonilab.mapper.StyleMapper.STYLE_MAPPER;
import static com.nonilab.mapper.ValidationMapper.VALIDATION_MAPPER;
import static com.nonilab.model.enums.ExceptionMessages.ELEMENT_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElementServiceHandler implements ElementService {

    private final ElementRepository elementRepository;

//    @Override
//    @Transactional
//    public List<ElementResponse> getAllElements() {
//        log.info("ActionLog.ElementServiceHandler.searchElements");
//        var elementEntity = elementRepository.findAllElements();
//        return ELEMENT_MAPPER.toElementResponses(elementEntity);
//    }

    @Override
    @Transactional
    public List<ElementResponse> searchElements(String elementType, String elementName, String screenName, String language) {
        log.info("ActionLog.ElementServiceHandler.searchElements {}, {}, {}, {}", elementType, elementName, screenName, language);
        var elementEntity = elementRepository.findByDynamicSearch(elementType, elementName, screenName, language);
        return ELEMENT_MAPPER.toElementResponses(elementEntity);
    }

    @Override
    public ElementResponse getElementById(Long id) {
        log.info("ActionLog.ElementServiceHandler.getElementById {}", id);
        return ELEMENT_MAPPER.toElementResponse(fetchElementIfExists(id));
    }

    @Override
    public void createElement(ElementRequest elementRequest) {
        log.info("ActionLog.ElementServiceHandler.createElement {}", elementRequest);
        var elementEntity = ELEMENT_MAPPER.toElementEntity(elementRequest);

        var propertyEntity = PROPERTY_MAPPER.toPropertyEntity(elementRequest.getProperty(), elementEntity);
        ELEMENT_MAPPER.setPropertyToElement(elementEntity, propertyEntity);

        var styleElement = STYLE_MAPPER.toStyleEntity(elementRequest.getProperty().getStyle(), propertyEntity);
        PROPERTY_MAPPER.setStyleToProperty(propertyEntity, styleElement);

        var optionEntity = setOptionsToProperties(propertyEntity, elementRequest);
        PROPERTY_MAPPER.setOptionToProperty(propertyEntity, optionEntity);

        var validationElement = setValidationToProperties(propertyEntity, elementRequest);
        PROPERTY_MAPPER.setValidationToProperty(propertyEntity, validationElement);

        elementRepository.save(elementEntity);
    }

    @Override
    public void updateElement(Long id, ElementRequestForUpdate elementRequest) {
        log.info("ActionLog.ElementServiceHandler.updateElement start: {}", elementRequest);
        var element = fetchElementIfExists(id);
        ELEMENT_MAPPER.updateElementEntity(element, elementRequest);
        PROPERTY_MAPPER.updatePropertyEntity(element.getPropertyEntity(), elementRequest.getProperty());
        STYLE_MAPPER.updateStyleEntity(element.getPropertyEntity().getStyleEntity(), elementRequest.getProperty().getStyle());
        OPTION_MAPPER.updateOptions(element.getPropertyEntity().getOptionEntities(), elementRequest.getProperty().getOptionRequests());
        VALIDATION_MAPPER.updateValidations(element.getPropertyEntity().getValidationRuleEntities(), elementRequest.getProperty().getValidationRequests());
        elementRepository.save(element);
        log.info("ActionLog.ElementServiceHandler.updateElement end: {}", elementRequest);
    }

    @Override
    public void deleteElement(Long id) {
        log.info("ActionLog.ElementServiceHandler.deleteElement {}", id);
        elementRepository.deleteById(id);
    }


    private ElementEntity fetchElementIfExists(Long id) {
        return elementRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ELEMENT_NOT_FOUND.getMessage(), ELEMENT_NOT_FOUND.getCode()));
    }

    private List<OptionEntity> setOptionsToProperties(PropertyEntity propertyEntity, ElementRequest elementRequest) {
        return elementRequest.getProperty()
                .getOptionRequests()
                .stream()
                .map(optionRequest -> OPTION_MAPPER.toOptionEntity(propertyEntity, optionRequest))
                .toList();
    }

    private List<ValidationRuleEntity> setValidationToProperties(PropertyEntity propertyEntity, ElementRequest elementRequest) {
        return elementRequest.getProperty()
                .getValidationRequests()
                .stream()
                .map(validationRequest -> VALIDATION_MAPPER.toValidationEntity(propertyEntity, validationRequest))
                .toList();
    }
}
