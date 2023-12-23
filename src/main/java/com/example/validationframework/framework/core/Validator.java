package com.example.validationframework.framework.core;

import java.lang.reflect.Field;

public abstract class Validator {
    protected abstract boolean invalid(Field field, Object value);
    protected abstract String getMessage(Field field);
    public final ConstraintViolation validate(Field field, Object object) {

        Object value = getValueFromObject(field, object);

        ConstraintViolation constraint = createConstraintViolation(field.getName(), value);

        if (this.invalid(field, value)) {
            constraint.setMessage(this.getMessage(field));
            constraint.setValid(false);
        }

        return constraint;
    }

    private ConstraintViolation createConstraintViolation(String property, Object value){
        ConstraintViolation constraint = new FieldConstraintViolation();

        constraint.setProperty(property);
        constraint.setValue(value);

        return constraint;
    }

    private Object getValueFromObject(Field field, Object object) {
        try {
            Object value = field.get(object);
            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


}
