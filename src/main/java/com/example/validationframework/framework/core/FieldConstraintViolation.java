package com.example.validationframework.framework.core;

public class FieldConstraintViolation implements ConstraintViolation{
    private String message;
    private Object value;
    private String property;
    private boolean valid = true;

    public FieldConstraintViolation() {

    }

    public FieldConstraintViolation(String message, Object value, String property, boolean valid) {
        this.message = message;
        this.value = value;
        this.property = property;
        this.valid = valid;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String getProperty() {
        return this.property;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
