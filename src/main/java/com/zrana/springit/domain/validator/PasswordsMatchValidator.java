package com.zrana.springit.domain.validator;

import com.zrana.springit.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordMatch, User> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) { }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
