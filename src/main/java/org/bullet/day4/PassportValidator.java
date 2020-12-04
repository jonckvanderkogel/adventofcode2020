package org.bullet.day4;

import javax.validation.Validation;
import javax.validation.Validator;

public class PassportValidator {
    private final static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public boolean isValidPassport(Passport passport) {
        return validator.validate(passport).isEmpty();
    }
}
