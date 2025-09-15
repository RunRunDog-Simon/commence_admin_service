package com.gtelant.commerce_admin_service.requests;

import com.gtelant.commerce_admin_service.models.Category;
import com.gtelant.commerce_admin_service.models.Poster;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreatePosterRequest {
    private String posterName;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer stock;

    private String reference;
    private String imageUrl;
    private String thumbnailUrl;
    private String description;

    @NotNull
    private Long categoryId;
}
