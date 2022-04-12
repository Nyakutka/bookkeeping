package com.tsapov.bookkeeping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookkeepingApplication {

    private static final Logger log = LoggerFactory.getLogger(BookkeepingApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BookkeepingApplication.class, args);
    }

}
