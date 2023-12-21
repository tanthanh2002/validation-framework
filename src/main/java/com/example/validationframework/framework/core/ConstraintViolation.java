package com.example.validationframework.framework.core;

public interface ConstraintViolation {
    String getMessage();
    void setMessage(String message);
    Object getValue();
    void setValue(Object value);
    String getProperty();
    void setProperty(String property);
    boolean isValid();
    void setValid(boolean valid);
}

