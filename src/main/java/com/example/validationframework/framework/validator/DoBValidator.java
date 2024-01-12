package com.example.validationframework.framework.validator;

import com.example.validationframework.framework.annotation.DateOfBirth;
import com.example.validationframework.framework.core.Validator;
import com.example.validationframework.framework.util.ValidatorUtil;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class DoBValidator extends Validator {
    @Override
    protected boolean invalid(Field field, Object value) {
        return !ValidatorUtil.isDateOfBirth((LocalDate) value);
    }

    @Override
    protected String getMessage(Field field) {
        DateOfBirth annotation = field.getDeclaredAnnotation(DateOfBirth.class);
        return annotation.message();
    }
}