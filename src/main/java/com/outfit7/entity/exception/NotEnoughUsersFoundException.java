package com.outfit7.entity.exception;

public class NotEnoughUsersFoundException extends RuntimeException {
    public NotEnoughUsersFoundException(String message) {
        super(message);
    }
}
