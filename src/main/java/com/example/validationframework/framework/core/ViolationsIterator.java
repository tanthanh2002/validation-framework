package com.example.validationframework.framework.core;

public class ViolationsIterator implements IIterator<ConstraintViolation> {
    private ConstraintViolation[] elements;
    private int index;

    public ViolationsIterator(ConstraintViolation[] elements) {
        this.elements = elements;
        this.index = 0;
    }

    @Override
    public ConstraintViolation getNext() {
        if (hasMore()) {
            ConstraintViolation element = elements[index];
            index++;
            return element;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasMore() {
        return index < elements.length;
    }
}