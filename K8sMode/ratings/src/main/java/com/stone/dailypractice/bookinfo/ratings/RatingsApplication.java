package com.stone.dailypractice.bookinfo.ratings;

import com.stone.dailypractice.bookinfo.common.log.LoggerAspect;
import com.stone.dailypractice.bookinfo.common.mesh.FeginInterceptor;
import com.stone.dailypractice.bookinfo.common.mesh.FeignConfiguration;
import com.stone.dailypractice.bookinfo.common.mesh.HystrixRequestAttributeAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = "com.stone.dailypractice.bookinfo.ratings")
@SpringBootApplication
@Import({LoggerAspect.class, FeginInterceptor.class, FeignConfiguration.class, HystrixRequestAttributeAutoConfiguration.class})
public class RatingsApplication {
    public static void main(String[] args) {
        SpringApplication.run( RatingsApplication.class, args);
    }
}