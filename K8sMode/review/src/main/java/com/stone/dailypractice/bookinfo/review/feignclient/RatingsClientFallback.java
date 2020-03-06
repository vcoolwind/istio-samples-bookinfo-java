package com.stone.dailypractice.bookinfo.review.feignclient;

import org.springframework.stereotype.Component;

@Component
public class RatingsClientFallback implements RatingsClient {
    @Override
    public String getRatings(String productId) {
        return "{}";
    }
}
