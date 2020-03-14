package com.stone.dailypractice.bookinfo.reviews.ratings;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;


// @FeignClient(name = "${ratings.servicename}" ,fallback = RatingsClientFallback.class)
@FeignClient(name = "${ratings.servicename}", fallbackFactory = RatingsClientFallbackFactory.class)
public interface RatingsClient {

    @GetMapping(value = "/ratings/{productId}/{reviewer}")
    Rating getRating(@PathVariable("productId") UUID productId, @PathVariable("reviewer") String reviewer);

    @PostMapping(value = "/ratings")
    ResponseEntity<Rating> createRating(@RequestBody Rating rating);
}
