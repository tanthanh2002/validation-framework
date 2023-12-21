package com.example.validationframework.framework.validator;

import com.example.validationframework.framework.annotation.NotNull;
import com.example.validationframework.framework.core.Validator;

import java.lang.reflect.Field;

public class NotNullValidator extends Validator {
    @Override
    protected boolean invalid(Field field, Object value) {
        return (value == null);
    }

    @Override
    protected String getMessage(Field field) {
        NotNull annotation = field.getDeclaredAnnotation(NotNull.class);
        return annotation.message();
    }
}
