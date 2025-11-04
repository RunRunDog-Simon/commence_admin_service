package com.gtelant.commerce_admin_service.requests;

import com.gtelant.commerce_admin_service.models.User;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String role;

    public RegisterRequest(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
