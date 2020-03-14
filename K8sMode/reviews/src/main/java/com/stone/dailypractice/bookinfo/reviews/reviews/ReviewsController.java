package com.stone.dailypractice.bookinfo.reviews.reviews;

import com.stone.dailypractice.bookinfo.reviews.ratings.Rating;
import com.stone.dailypractice.bookinfo.reviews.ratings.RatingsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ReviewsController {

    @Value("${ratings.enabled}")
    private Boolean ratingsEnabled;

    private ReviewsRepository reviewRepository;

    private RatingsClient ratingsClient;

    public ReviewsController(ReviewsRepository reviewRepository, RatingsClient ratingsClient) {
        this.reviewRepository = reviewRepository;
        this.ratingsClient = ratingsClient;
    }

    @GetMapping(value = "/")
    public String root() {
        return "hello reviews";
    }


    @GetMapping(value = "/reviews/{productId}")
    public ReviewDto getReviewsByProductId(@PathVariable UUID productId) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setProductId(productId);

        List<Review> reviews = reviewRepository.findByProductId(productId);
        reviewDto.setReviews(reviews);

        for (Review review : reviews) {
            if (this.ratingsEnabled) {
                Rating rating = ratingsClient.getRating(productId,review.getReviewer());
                review.setRating(rating);
            }
        }

        return reviewDto;
    }

    // curl -d '{"reviewerId":"9bc908be-0717-4eab-bb51-ea14f669ef20","productId":"a071c269-369c-4f79-be03-6a41f27d6b5f","review":"This was OK.","rating":3}' -H "Content-Type: application/json" -X POST http://localhost:8102/reviews
//    @PostMapping(value = "/reviews")
//    public ReviewDto createReview(@RequestBody ReviewDto reviewDto) {
//        if (this.ratingsEnabled) {
//            Rating rating = new Rating(null, reviewDto.getReviewerId(), reviewDto.getProductId(), reviewDto.getRating());
//            ResponseEntity<Rating> ratingResponse = ratingsClient.createRating(rating);
//
//            // No way to propagate exception from the fallback, so we need to create it again
//            if (ratingResponse.getStatusCode() != HttpStatus.OK) {
//                throw new RatingsUnavailableException();
//            }
//        }
//
//        Review review = new Review(null, reviewDto.getReviewerId(), reviewDto.getProductId(), reviewDto.getReview());
//        review = reviewRepository.save(review);
//
//        reviewDto.setId(review.getId());
//        return reviewDto;
//    }
}