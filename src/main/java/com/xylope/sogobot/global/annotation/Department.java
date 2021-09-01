package com.xylope.sogobot.global.annotation;

import com.xylope.sogobot.global.validator.DepartmentValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentValidator.class)
public @interface Department {
    String message() default "Sorry, string value isn't department value";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
