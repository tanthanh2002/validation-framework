package com.example.validationframework.framework.validator;

import com.example.validationframework.framework.annotation.NotBlank;
import com.example.validationframework.framework.core.Validator;

import java.lang.reflect.Field;

public class NotBlankValidator extends Validator {
    @Override
    protected boolean invalid(Field field, Object value) {
        if(value instanceof String) {
            return ((String) value).isBlank();
        }
        return false;
    }

    @Override
    protected String getMessage(Field field) {
        NotBlank annotation = field.getDeclaredAnnotation(NotBlank.class);
        return annotation.message();
    }
}
