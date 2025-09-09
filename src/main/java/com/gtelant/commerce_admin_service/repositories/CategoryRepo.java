package com.gtelant.commerce_admin_service.repositories;

import com.gtelant.commerce_admin_service.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
