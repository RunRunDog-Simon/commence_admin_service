package com.gtelant.commerce_admin_service.requests;

import lombok.Data;

@Data // 為何只要Data?
public class UpdateCategoryRequest {
    private String categoryName;
}
