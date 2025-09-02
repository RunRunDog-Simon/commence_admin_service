package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.repositories.UserCommerceRepo;
import com.gtelant.commerce_admin_service.requests.CreateUserRequest;
import com.gtelant.commerce_admin_service.responses.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCommerceService {
    private final UserCommerceRepo userCommerceRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserCommerceService(UserCommerceRepo userCommerceRepo){
        this.userCommerceRepo = userCommerceRepo;
    }

    public List<User> findAllUsers() {
        List<User> users = userCommerceRepo.findAll();
        return users;
    }
    public Optional<User> findUserById(long id){
        Optional<User> user = userCommerceRepo.findById(id); //long int沒有重載?
        return user;
    }

    public GetUserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setHasNewsletter(request.isHasNewsletter());
        user.setRole(request.getRole());
        User savedUser = userCommerceRepo.save(user);
        GetUserResponse response = new GetUserResponse(savedUser);
        return response;
    }
}
