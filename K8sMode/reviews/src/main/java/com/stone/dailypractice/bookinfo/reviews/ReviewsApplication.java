package com.stone.dailypractice.bookinfo.reviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.stone.dailypractice.bookinfo.reviews.ratings")
public class ReviewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewsApplication.class, args);
    }
}
