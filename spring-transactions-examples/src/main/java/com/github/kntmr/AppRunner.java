package com.github.kntmr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        executeInterface();
        //executeAbstract();
        //executeConcrete();
    }

    @Autowired
    BaseService baseService;

    void executeInterface() {
        logger.info("Call executeInterface() ...");

        baseService.register("foo", "bar", "buz");
        for (String name : baseService.findAll()) {
            logger.info("--> " + name);
        }

        try {
            baseService.register("xxxx", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        for (String name : baseService.findAll()) {
            logger.info("--> " + name);
        }
    }

    @Autowired
    AbstractService abstractService;

    void executeAbstract() {
        logger.info("Call executeAbstract() ...");

        abstractService.register("foo", "bar", "buz");

        for (String name : abstractService.findAll()) {
            logger.info("--> " + name);
        }

        try {
            abstractService.register("xxxx", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        for (String name : abstractService.findAll()) {
            logger.info("--> " + name);
        }
    }

    @Autowired
    CustomerService concreteService;

    void executeConcrete() {
        logger.info("Call executeConcrete() ...");

        concreteService.register("foo", "bar", "buz");

        for (String name : concreteService.findAll()) {
            logger.info("--> " + name);
        }

        try {
            concreteService.register("xxxx", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        for (String name : concreteService.findAll()) {
            logger.info("--> " + name);
        }
    }

}
