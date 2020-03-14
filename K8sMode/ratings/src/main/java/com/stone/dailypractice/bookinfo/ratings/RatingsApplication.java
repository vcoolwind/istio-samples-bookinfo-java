package com.stone.dailypractice.bookinfo.ratings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.stone.dailypractice.bookinfo.ratings")
@SpringBootApplication
public class RatingsApplication {
    public static void main(String[] args) {
        SpringApplication.run( RatingsApplication.class, args);
    }
}