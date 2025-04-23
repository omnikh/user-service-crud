package com.kapstone.challenge.userservice.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UserNotFoundException{" + ", message='" + getMessage() + '\'' + '}';
    }

}

