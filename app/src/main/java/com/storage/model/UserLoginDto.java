package com.storage.model;

public class UserLoginDto {

    private final String email;
    private final String password;
    private final String deviceID;

    public UserLoginDto(String email, String password, String deviceID) {
        this.email = email;
        this.password = password;
        this.deviceID = deviceID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDeviceID() {
        return deviceID;
    }
}
