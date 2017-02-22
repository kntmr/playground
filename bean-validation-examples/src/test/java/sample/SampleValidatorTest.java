package sample;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class SampleValidatorTest {

    @Test
    public void test() {
        SampleBean bean = new SampleBean("foo", "bar");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<SampleBean>> constraintViolations = validator.validate(bean);
        constraintViolations.forEach(violation -> {
            System.out.println(violation.getMessage() + ": [" + violation.getPropertyPath().toString() + "]");
        });
    }

    static class SampleBean {
        @SampleValidation(param = "hoge")
        private String name;
        @SampleValidation(param = "fuga")
        private String value;
        public SampleBean(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

}
