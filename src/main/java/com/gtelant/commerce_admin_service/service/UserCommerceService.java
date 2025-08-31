package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.repositories.UserCommerceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommerceService {
    private final UserCommerceRepo userCommerceRepo;
    @Autowired
    public UserCommerceService(UserCommerceRepo userCommerceRepo){
        this.userCommerceRepo = userCommerceRepo;
    }

    public List<User> findAllUsers() {
        List<User> users = userCommerceRepo.findAll();
        return users;
    }
}
