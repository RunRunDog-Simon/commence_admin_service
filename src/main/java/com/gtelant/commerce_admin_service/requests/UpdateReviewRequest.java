package com.gtelant.commerce_admin_service.requests;

import lombok.Data;

@Data
public class UpdateReviewRequest {
    private int rating;
    private String Comment;
}
