package com.shoppingapp.main.dto;

public class UserRequestDto {

    private String name;
    private String email;
    private String role;

   
    public UserRequestDto() {
    }

    // All-Args Constructor
    public UserRequestDto(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}