package com.github.kntmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCacheExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheExamplesApplication.class, args);
    }

}
