package com.laxman.job.server.dto;

public class SuccessResponse {

    private boolean success = true;
    private String message;

    public SuccessResponse(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
