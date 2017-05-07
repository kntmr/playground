package com.github.kntmr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("...fetching books");
        logger.info("1234 --> " + bookRepository.getByIsbn("1234"));
        logger.info("5678 --> " + bookRepository.getByIsbn("5678"));
        logger.info("1234 --> " + bookRepository.getByIsbn("1234"));
        logger.info("5678 --> " + bookRepository.getByIsbn("5678"));
        logger.info("9999 --> " + bookRepository.getByIsbn("9999"));
        logger.info("5678 --> " + bookRepository.getByIsbn("5678"));
    }

}
