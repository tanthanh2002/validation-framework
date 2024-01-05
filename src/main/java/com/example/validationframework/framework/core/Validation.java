package com.example.validationframework.framework.core;

import com.example.validationframework.framework.enums.ValidatorType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Validation {
    private static Validation instance = new Validation();
    private Validation() {

    }
    public static Validation getInstance() {
        return instance;
    }

    public ValidationResult validate(Object object) {
        ValidationResult validationResult = new ValidationResult();

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);

            Annotation[] annotations = field.getAnnotations();

            for(Annotation annotation : annotations) {
                ValidatorType validatorType = ValidatorType.getType(annotation.annotationType());

                if (validatorType == null) {
                    continue;
                }

                Validator validator = ValidatorFactory.createValidator(validatorType);
                if (validator == null) {
                    continue;
                }

                ConstraintViolation constraintViolation = validator.validate(field, object);
                if (!constraintViolation.isValid()) {
                    validationResult.addConstraintViolation(constraintViolation);
                }
            }
        }

        return validationResult;
    }
}
