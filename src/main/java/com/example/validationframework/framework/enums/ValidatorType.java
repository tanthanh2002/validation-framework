package com.example.validationframework.framework.enums;

import java.lang.annotation.Annotation;
import com.example.validationframework.framework.annotation.*;

public enum ValidatorType {
    PHONE_NUMBER(PhoneNumber.class),
    REGEX(Regex.class),
    NOT_NULL(NotNull.class),
    MAX(Max.class),
    MIN(Min.class),
    DATE_OF_BIRTH(DateOfBirth.class),
    NOT_BLANK(NotBlank.class),
    BLANK(Blank.class);
    private Class<? extends Annotation> value;
    private ValidatorType(Class<? extends Annotation> value) {
        this.value = value;
    }

    public Class<? extends Annotation> getValue() {
        return this.value;
    }

    public static ValidatorType getType(Class<? extends Annotation> annotationType) {
        for (ValidatorType validatorType : ValidatorType.values()) {
            if (annotationType == validatorType.getValue()) {
                return validatorType;
            }
        }
        return null;
    }
}