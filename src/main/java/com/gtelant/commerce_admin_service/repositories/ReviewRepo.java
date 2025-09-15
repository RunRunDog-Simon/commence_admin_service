package com.gtelant.commerce_admin_service.repositories;

import com.gtelant.commerce_admin_service.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo  extends JpaRepository<Review, Long> {
}
