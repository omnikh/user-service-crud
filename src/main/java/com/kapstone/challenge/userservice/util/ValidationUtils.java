package com.kapstone.challenge.userservice.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
@Validated
public class ValidationUtils {

    private final Validator validator;

    public ValidationUtils(Validator validator) {
        this.validator = validator;
    }

    public <T> void validateDto(T dto) throws ConstraintViolationException {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (ConstraintViolation<T> violation : violations) {
                if (!first) sb.append(", ");
                sb.append(violation.getMessage());
                first = false;
            }
            throw new ConstraintViolationException("" + sb, violations);
        }
    }
}
