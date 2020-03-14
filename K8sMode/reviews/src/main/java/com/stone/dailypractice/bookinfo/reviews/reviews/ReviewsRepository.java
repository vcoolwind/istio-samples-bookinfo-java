package com.stone.dailypractice.bookinfo.reviews.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewsRepository extends JpaRepository<Review, UUID> {

    List<Review> findByProductId(UUID productId);

}
