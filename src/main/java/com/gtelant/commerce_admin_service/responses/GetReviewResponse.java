package com.gtelant.commerce_admin_service.responses;

import com.gtelant.commerce_admin_service.enums.ReviewStatus;
import com.gtelant.commerce_admin_service.models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetReviewResponse {
    private GetUserResponse user;
    private GetPosterResponse poster;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GetReviewResponse(Review review) {
        this.user = new GetUserResponse(review.getUser());
        this.poster = new GetPosterResponse(review.getPoster());
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
    }
}
