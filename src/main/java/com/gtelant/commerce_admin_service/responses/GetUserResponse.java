package com.gtelant.commerce_admin_service.responses;
import com.gtelant.commerce_admin_service.models.User;

public class GetUserResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String role;
    public GetUserResponse() {
    }
    public GetUserResponse(User user) {
        this.userId =  user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
