package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.repositories.UserRepo;
import com.gtelant.commerce_admin_service.requests.LoginRequest;
import com.gtelant.commerce_admin_service.requests.RegisterRequest;
import com.gtelant.commerce_admin_service.responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtAuthService {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    @Autowired
    public JwtAuthService(UserRepo userRepo, JwtService jwtService){
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request){
        // 1. 建立User
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepo.save(user);

        //2. 產出token
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(LoginRequest request){
        // 1. 找到對應的User
        Optional<User> userOptional = userRepo.findByEmail(request.getEmail());
        if(userOptional.isPresent()){
            User user =userOptional.get();
            if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                // 2. 產出token
                String jwtToken = jwtService.generateToken(user);
                return new AuthResponse(jwtToken);
            }
        }
        throw new RuntimeException("無效憑證");
    }
}
