package com.stone.dailypractice.bookinfo.reviews.reviews;

import com.stone.dailypractice.bookinfo.reviews.configreload.RemoteConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private UUID productId;
    private List<Review> reviews;

    private RemoteConfig remoteConfig;
}
