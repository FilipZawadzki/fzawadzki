package com.test.demo.utils.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PostCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostCodeConstraint {
    String message() default "Invalid post code - should be 'xx-xxx'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

