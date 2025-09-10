package com.gtelant.commerce_admin_service.requests;

import com.gtelant.commerce_admin_service.models.Category;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreatePosterRequest {
    private String posterName;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal price;
    private int stock;
    private String reference;
    private String imageUrl;
    private String thumbnailUrl;
    private String description;
    private Category category;
}
