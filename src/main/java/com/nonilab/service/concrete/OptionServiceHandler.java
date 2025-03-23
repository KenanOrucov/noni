package com.nonilab.service.concrete;

import com.nonilab.dao.entity.OptionEntity;
import com.nonilab.dao.entity.PropertyEntity;
import com.nonilab.dao.repository.OptionRepository;
import com.nonilab.dao.repository.PropertyRepository;
import com.nonilab.exception.NotFoundException;
import com.nonilab.service.abstraction.OptionService;
import com.nonilab.service.abstraction.PropertyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.nonilab.model.enums.ExceptionMessages.PROPERTY_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionServiceHandler implements OptionService {

    private final OptionRepository optionRepository;

    @Override
    @Transactional
    public void deleteOption(Long id) {
        log.info("ActionLog.OptionServiceHandler.deleteOption start: {}", id);
        var option = fetchOptionIfExists(id);
        option.getPropertyEntity().getOptionEntities().remove(option);
        optionRepository.delete(option);
    }

    private OptionEntity fetchOptionIfExists(Long id) {
        return optionRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(PROPERTY_NOT_FOUND.getMessage(), PROPERTY_NOT_FOUND.getCode()));
    }
}
