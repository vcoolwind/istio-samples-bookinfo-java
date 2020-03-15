package com.stone.dailypractice.bookinfo.reviews.ratings;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RatingsClientFallbackFactory implements FallbackFactory<RatingsClient> {

    @Override
    public RatingsClient create(Throwable throwable) {
        log.error("RatingsClient invoke method error", throwable);
        return new RatingsClientFallback() ;
    }
}