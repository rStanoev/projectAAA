package com.example.projecta.domain.dto.model;

public class UserNotFoundException extends RuntimeException{

    private final String userString;

    public UserNotFoundException(String userString) {

        super("User with ID " + userString + " not found!");


        this.userString = userString;

    }

    public String getuserString() {
        return userString;
    }


}
