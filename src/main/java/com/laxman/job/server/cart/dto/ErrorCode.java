package com.laxman.job.server.cart.dto;

public enum ErrorCode {

    INTERNAL_SERVER_ERROR("Something went wrong. Please try again later."),
    DB_CONSTRAINT_ERROR("Invalid cart data");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
