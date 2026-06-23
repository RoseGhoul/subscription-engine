package com.subscriptionengine.usagemanagement.domain.validation;

import com.subscriptionengine.usagemanagement.domain.model.Usage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Set;

@Component
public class UsageValidator {

    private final Validator validator;

    public UsageValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(Usage usage) {
        Set<ConstraintViolation<Usage>> violations = validator.validate(usage);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        if (usage.getUsageDate() != null && usage.getUsageDate().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Usage date cannot be in the future");
        }
        
        if (usage.getUsageType() == null || usage.getUsageType().trim().isEmpty()) {
            throw new IllegalArgumentException("Usage type is required");
        }
    }
}
