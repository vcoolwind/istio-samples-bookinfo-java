package com.stone.dailypractice.bookinfo.ratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RatingsRepository extends JpaRepository<Rating, UUID> {

    @Query("select r from Rating r where r.productId = ?1 and r.reviewer = ?2")
    Rating findByProductId(UUID productId, String reviewer);
}
