package com.gtelant.commerce_admin_service.repositories;

import com.gtelant.commerce_admin_service.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    //以下可以被Specification取代
    Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName, PageRequest pageRequest
    );
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);

    // 以下新增EMAIL查詢方法
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
