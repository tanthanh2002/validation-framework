package com.example.validationframework.framework.core;

import com.example.validationframework.framework.enums.ValidatorType;
import com.example.validationframework.framework.validator.BlankValidator;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class ValidatorFactory {
    private static Map<Class<? extends Annotation>, Validator> validatorMap = new HashMap<>();
    public static Validator createValidator(ValidatorType validatorType) {
        Validator validator = validatorMap.get(validatorType.getValue());

        if (validator == null){
            switch (validatorType){
                case BLANK:
                    validator = new BlankValidator();
            }
            validatorMap.put(validatorType.getValue(), validator);
        }
        
        return validator;
    }
}
