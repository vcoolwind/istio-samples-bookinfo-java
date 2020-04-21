package com.stone.dailypractice.bookinfo.reviews;

import com.stone.dailypractice.bookinfo.common.log.LoggerAspect;
import com.stone.dailypractice.bookinfo.common.mesh.FeginInterceptor;
import com.stone.dailypractice.bookinfo.common.mesh.FeignConfiguration;
import com.stone.dailypractice.bookinfo.common.mesh.HystrixRequestAttributeAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@Import({LoggerAspect.class, FeginInterceptor.class, FeignConfiguration.class, HystrixRequestAttributeAutoConfiguration.class})
public class ReviewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReviewsApplication.class, args);
    }
}
