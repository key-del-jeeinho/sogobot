package com.xylope.sogobot.global.validator;
import com.xylope.sogobot.global.annotation.Department;
import com.xylope.sogobot.global.enum_type.DepartmentType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentValidator implements ConstraintValidator<Department, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        DepartmentType.of(value);
        return true;
    }
}
