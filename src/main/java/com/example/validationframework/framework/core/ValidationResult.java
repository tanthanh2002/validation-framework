package com.example.validationframework.framework.core;

import java.util.HashSet;
import java.util.Set;

public class ValidationResult implements IterableCollection<ConstraintViolation> {
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

    @Override
    public ViolationsIterator createIterable() {
        return new ViolationsIterator(violations.toArray(new ConstraintViolation[0]));
    }
}