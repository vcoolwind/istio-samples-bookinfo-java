package com.stone.dailypractice.bookinfo.ratings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
public class RatingsController {
    private Logger LOG = LoggerFactory.getLogger(RatingsController.class);

    private RatingsRepository ratingsRepository;

    public RatingsController(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    @GetMapping(value = "/")
    public String root() {
        return "hello ratings";
    }


    @GetMapping(value = "/ratings/{productId}/{reviewer}")
    public Rating getRatingsByProduct(@PathVariable("productId") UUID productId, @PathVariable("reviewer") String reviewer, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        LOG.info("----------getRatingsByProduct--------" + ipAddress);
        return ratingsRepository.findByProductId(productId, reviewer);
    }

    // curl -d '{"reviewerId":"9bc908be-0717-4eab-bb51-ea14f669ef20","productId":"a071c269-369c-4f79-be03-6a41f27d6b5f","rating":3}' -H "Content-Type: application/json" -X POST http://localhost:8101/ratings
    @PostMapping(value = "/ratings")
    public Rating createRating(@RequestBody Rating rating) {
        return ratingsRepository.save(rating);
    }
}