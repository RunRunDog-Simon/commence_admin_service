package com.gtelant.commerce_admin_service.controllers;

import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.requests.LoginRequest;
import com.gtelant.commerce_admin_service.requests.RegisterRequest;
import com.gtelant.commerce_admin_service.responses.AuthResponse;
import com.gtelant.commerce_admin_service.service.JwtAuthService;
import com.gtelant.commerce_admin_service.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
@Tag(name = "JWT驗證", description = "提供使用者登入註冊")
public class JwtAuthController {
    private final UserService userService;
    private final JwtAuthService jwtAuthService;
    @Autowired
    public JwtAuthController(UserService userService, JwtAuthService jwtAuthService){
        this.userService = userService;
        this.jwtAuthService = jwtAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user){
        return ResponseEntity.ok(jwtAuthService.register(new RegisterRequest(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return  ResponseEntity.ok(jwtAuthService.login(request));
    }
}
