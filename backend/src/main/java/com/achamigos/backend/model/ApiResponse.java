package com.achamigos.backend.model;

import com.achamigos.backend.model.User;

public class ApiResponse {
    private String status;
    private String message;
    private User user; // 🔹


    public ApiResponse() {}


    public ApiResponse(String status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }


    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }


    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
