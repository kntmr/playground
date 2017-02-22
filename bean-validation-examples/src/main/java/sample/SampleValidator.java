package sample;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SampleValidator implements ConstraintValidator<SampleValidation, String> {

    private String param;

    public void initialize(SampleValidation constraintAnnotation) {
        this.param = constraintAnnotation.param();
        System.out.println("@initialize : param=" + param + ", hashCode=" + hashCode());
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("@isValid : param=" + param + ", hashCode=" + hashCode() + ", value=" + value);
        if (value == null) {
            return true;
        }
        return !"".equals(value);
    }

}
