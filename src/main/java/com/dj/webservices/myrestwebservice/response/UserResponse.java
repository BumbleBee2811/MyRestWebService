package com.dj.webservices.myrestwebservice.response;

import com.dj.webservices.myrestwebservice.bean.User;

public class UserResponse {
    private User user;
    private int status;
    private String message;
    private long timestamp;

    public User getUser() {
        return user;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
