package com.gtelant.commerce_admin_service.repositories;

import com.gtelant.commerce_admin_service.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor {
//    Page <User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
//            String firstName, String lastName, PageRequest pageRequest
//    );
//
//    Page<User> findByHasNewsletterTrue(Pageable pageable);
//
//

//    Optional<User> findByLastNameAndFirstName(String lastName, String firstName);
//    以上這行ChatGPT提供以下解法
//    @Query("select u from User u where u.lastName = :lastName and u.firstName = :firstName")
//    Optional<User> findByFullName(@Param("lastName") String ln,
//                                  @Param("firstName") String fn);
//    Optional<User> findByEmail(String email);
}
