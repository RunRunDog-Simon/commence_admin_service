package com.gtelant.commerce_admin_service.requests;

import com.gtelant.commerce_admin_service.models.User;
import jakarta.persistence.Column;

public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private  boolean hasNewsletter;

    public UpdateUserRequest() {
    }


    public UpdateUserRequest(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.state = user.getState();
        this.zipcode = user.getZipcode();
        this.hasNewsletter = user.isHasNewsletter();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isHasNewsletter() {
        return hasNewsletter;
    }

    public void setHasNewsletter(boolean hasNewsletter) {
        this.hasNewsletter = hasNewsletter;
    }
}
