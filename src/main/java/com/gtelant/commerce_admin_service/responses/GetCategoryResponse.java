package com.gtelant.commerce_admin_service.responses;

import com.gtelant.commerce_admin_service.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryResponse {
    private long id;
    private String categoryName;

    public GetCategoryResponse(Category category){
        this.id = category.getCategoryId();
        this.categoryName = category.getCategoryName();
    }
}
