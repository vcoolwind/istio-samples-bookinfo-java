package com.stone.dailypractice.bookinfo.review.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="${feign.product-infra.name:product-infra-service}",fallback = RatingsClientFallback.class)
public interface RatingsClient {
    String getRatings(String productId);
}
