package com.stone.dailypractice.bookinfo.common.mesh;

import com.netflix.hystrix.Hystrix;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Reno Chou
 */
@Configuration
@ConditionalOnClass({ Hystrix.class })
public class HystrixRequestAttributeAutoConfiguration {

    @Bean
    public RequestAttributeHystrixConcurrencyStrategy hystrixRequestAutoConfiguration() {
        return new RequestAttributeHystrixConcurrencyStrategy();
    }
}