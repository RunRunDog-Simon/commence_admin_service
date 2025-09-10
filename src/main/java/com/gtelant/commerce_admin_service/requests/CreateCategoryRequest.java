package com.gtelant.commerce_admin_service.requests;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    private String categoryName;
}
