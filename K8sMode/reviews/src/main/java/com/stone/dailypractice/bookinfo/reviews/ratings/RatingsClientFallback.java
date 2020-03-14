package com.stone.dailypractice.bookinfo.reviews.ratings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RatingsClientFallback implements RatingsClient {
    private Logger LOG = LoggerFactory.getLogger(RatingsClientFallback.class);

    @Override
    public Rating getRating(UUID productId, String reviewer) {
        LOG.info("Fallback for getRatingsByProduct " + productId);
        Rating rating = new Rating();
        rating.setProductId(productId);
        rating.setReviewer(reviewer);

        rating.setError("Cann't get rating for productId :" + productId);
        return rating;
    }

    @Override
    public ResponseEntity<Rating> createRating(Rating rating) {
        LOG.info("Fallback for createRating " + rating);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
