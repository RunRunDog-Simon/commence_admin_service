package com.gtelant.commerce_admin_service.requests;

import com.gtelant.commerce_admin_service.models.Category;
import com.gtelant.commerce_admin_service.models.Poster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePosterRequest {
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

    public UpdatePosterRequest (Poster poster){
     this.posterName = poster.getPosterName();
     this.width = poster.getWidth();
     this.height = poster.getHeight();
     this.price = poster.getPrice();
     this.stock = poster.getStock();
     this.reference = poster.getReference();
     this.imageUrl = poster.getImageUrl();
     this.thumbnailUrl = poster.getThumbnailUrl();
     this.description = poster.getDescription();
     this.category = poster.getCategory();
    }
}
