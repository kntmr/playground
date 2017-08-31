package com.example.demo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = { SampleValidator.class })
public @interface SampleValidation {

    String value();

    String message() default "{com.example.demo.constraint.SampleValidation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
