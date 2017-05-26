package com.github.kntmr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final ApplicationContext applicationContext;

    public AppRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... strings) throws Exception {

        AbstractService service = (AbstractService) applicationContext.getBean("customerService");
        //CustomerService service = (CustomerService) applicationContext.getBean("customerService");

        service.insert("foo", "bar", "baz");

        for (String name : service.findAll()) {
            logger.info("--> " + name);
        }

        try {
            service.insert("hoge", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        for (String name : service.findAll()) {
            logger.info("--> " + name);
        }
    }

}
