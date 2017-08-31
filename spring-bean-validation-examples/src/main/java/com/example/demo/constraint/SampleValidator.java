package com.example.demo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class SampleValidator implements ConstraintValidator<SampleValidation, String> {

    String value;

    @Override
    public void initialize(SampleValidation constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Objects.equals(this.value, value);
    }

}
