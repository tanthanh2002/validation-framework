package com.example.validationframework.framework.validator;

import com.example.validationframework.framework.annotation.Blank;
import com.example.validationframework.framework.core.Validator;

import java.lang.reflect.Field;

public class BlankValidator extends Validator {
    @Override
    protected boolean invalid(Field field, Object value) {
        if(value instanceof String) {
            return ((String) value).isEmpty();
        }
        return false;
    }

    @Override
    protected String getMessage(Field field) {
        Blank annotation = field.getDeclaredAnnotation(Blank.class);
        return annotation.message();
    }
}
