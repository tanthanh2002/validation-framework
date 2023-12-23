package com.example.validationframework.framework.core;

import java.util.HashSet;
import java.util.Set;

public class ValidationResult {
    private Set<ConstraintViolation> violations;
    public ValidationResult() {
        violations = new HashSet<ConstraintViolation>();
    }
    public ValidationResult(Set<ConstraintViolation> violations) {
        this.violations = violations;
    }

    public Set<ConstraintViolation> getViolations() {
        return violations;
    }

    public boolean isValid() {
        return violations.isEmpty();
    }

    public void addConstraintViolation(ConstraintViolation violation) {
        violations.add(violation);
    }
}