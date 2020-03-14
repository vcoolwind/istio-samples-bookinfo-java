package com.stone.dailypractice.bookinfo.reviews.ratings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private final static String STAR_COLOR = System.getenv("STAR_COLOR") == null ? "black" : System.getenv("STAR_COLOR");
    private UUID id;
    private String reviewer;
    private UUID productId;
    private int stars;

    @Transient
    private String error="";
    @Transient
    private String color = STAR_COLOR;

}