package com.gtelant.commerce_admin_service.repositories;

import com.gtelant.commerce_admin_service.models.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PosterRepo extends JpaRepository<Poster, Long>, JpaSpecificationExecutor {
}
