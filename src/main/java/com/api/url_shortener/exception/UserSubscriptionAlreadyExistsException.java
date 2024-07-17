package com.api.url_shortener.exception;

public class UserSubscriptionAlreadyExistsException extends RuntimeException {
    public UserSubscriptionAlreadyExistsException(String message) {
        super(message);
    }
}
