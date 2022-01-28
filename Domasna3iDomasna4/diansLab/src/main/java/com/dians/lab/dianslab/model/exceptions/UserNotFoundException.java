package com.dians.lab.dianslab.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super(String.format("Username with id %s was not found",username));
    }
}