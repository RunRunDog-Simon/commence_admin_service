package com.gtelant.commerce_admin_service.requests;

import com.gtelant.commerce_admin_service.enums.ReviewStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateReviewRequest {
    private long userId;
    private long posterId;
    private int rating;
    private String Comment;
}
