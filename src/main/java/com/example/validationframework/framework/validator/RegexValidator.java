package com.example.validationframework.framework.validator;

import com.example.validationframework.framework.annotation.Regex;
import com.example.validationframework.framework.core.Validator;
import com.example.validationframework.framework.util.ValidatorUtil;

import java.lang.reflect.Field;

public class RegexValidator extends Validator {
    @Override
    protected boolean invalid(Field field, Object value) {
        Regex annotation = field.getDeclaredAnnotation(Regex.class);
        String pattern = annotation.pattern();
        return !ValidatorUtil.checkRegex((String) value, pattern);
    }

    @Override
    protected String getMessage(Field field) {
        Regex annotation = field.getDeclaredAnnotation(Regex.class);
        return annotation.message();
    }
}
