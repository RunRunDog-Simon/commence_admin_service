package com.gtelant.commerce_admin_service.responses;

import com.gtelant.commerce_admin_service.models.Category;
import com.gtelant.commerce_admin_service.models.Poster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryResponse {
    private long id;
    private String categoryName;
    private List<Poster> posters;

    public GetCategoryResponse(Category category){
        this.id = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.posters = category.getPosterList();
    }
}
