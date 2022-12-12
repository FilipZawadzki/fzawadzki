package com.test.demo.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PostCodeValidator implements ConstraintValidator<PostCodeConstraint, String> {

    @Override
    public void initialize(PostCodeConstraint postCode) {
    }

    @Override
    public boolean isValid(String postCode,
                           ConstraintValidatorContext cxt) {
        return postCode != null && postCode.matches("^[-0-9]*$")
                && (postCode.length() == 6);
    }

}
