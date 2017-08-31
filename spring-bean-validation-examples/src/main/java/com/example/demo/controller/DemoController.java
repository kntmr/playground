package com.example.demo.controller;

import com.example.demo.constraint.SampleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class DemoController {

    @Autowired
    MessageSource messageSource;

    @PostMapping("index")
    public String index(@RequestBody @Validated FooForm form, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream()
                    .map(e -> messageSource.getMessage(e, Locale.getDefault()))
                    .forEach(System.out::println);
            return "NG";
        }
        return "OK";
    }

    static class FooForm {
        @SampleValidation("foo")
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
