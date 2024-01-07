package com.example.validationframework.framework.validator;

import com.example.validationframework.framework.annotation.PhoneNumber;
import com.example.validationframework.framework.core.Validator;
import com.example.validationframework.framework.util.ValidatorUtil;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator extends Validator {
    @Override
    protected boolean invalid(Field field, Object value) {
        PhoneNumber annotation = field.getDeclaredAnnotation(PhoneNumber.class);
        String regex = "^[0-9]{10}$";
        ValidatorUtil.checkRegex((String) value, regex);
        return !ValidatorUtil.checkRegex((String) value, regex);
    }

    @Override
    protected String getMessage(Field field) {
        PhoneNumber annotation = field.getDeclaredAnnotation(PhoneNumber.class);
        return annotation.message();
    }
}
