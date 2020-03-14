package com.stone.dailypractice.bookinfo.reviews.ratings;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RatingsClientFallbackFactory implements FallbackFactory<RatingsClient> {
    private Logger LOG = LoggerFactory.getLogger(RatingsClientFallback.class);

    @Override
    public RatingsClient create(Throwable throwable) {
        LOG.warn("RatingsClient invoke method error", throwable);
        return new RatingsClientFallback() ;
    }
}