package com.nonilab.service.concrete;

import com.nonilab.dao.entity.StyleEntity;
import com.nonilab.dao.repository.StyleRepository;
import com.nonilab.exception.NotFoundException;
import com.nonilab.service.abstraction.StyleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.nonilab.model.enums.ExceptionMessages.STYLE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class StyleServiceHandler implements StyleService {

    private final StyleRepository styleRepository;

    @Override
    @Transactional
    public void deleteStyle(Long id) {
        log.info("ActionLog.StyleServiceHandler.deleteStyle start: {}", id);
        var style = fetchStyleIfExists(id);
        style.getPropertyEntity().setStyleEntity(null);
        styleRepository.delete(style);
    }

    private StyleEntity fetchStyleIfExists(Long id) {
        return styleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(STYLE_NOT_FOUND.getMessage(), STYLE_NOT_FOUND.getCode()));
    }
}
