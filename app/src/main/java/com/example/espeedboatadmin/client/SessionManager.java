package com.example.espeedboatadmin.client;

public class SessionManager {
    private String auth_token = "2|ib7dTa9FAfXOB6QgMnjtYY11cyVsqMIvXDpVMZxF";

    public void setAuthToken(String token) {
        this.auth_token = token;
    }

    public String getAuthToken() {
        return "Bearer "+auth_token;
    }
}
