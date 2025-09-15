package com.gtelant.commerce_admin_service.requests;

import com.gtelant.commerce_admin_service.models.Category;
import com.gtelant.commerce_admin_service.models.Poster;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    private Integer stock; // 這邊改為大寫Integer，不然Service無法設置null
    private String reference;
    private String imageUrl;
    private String thumbnailUrl;
    private String description;
    private Long categoryId;// 這邊改為大寫Long，不然Service無法設置null

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
     this.categoryId = poster.getCategory().getCategoryId();
    }
}
