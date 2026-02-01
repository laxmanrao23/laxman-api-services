package com.laxman.job.server.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.laxman.job.server.dto.ErrorResponse;
import com.mongodb.MongoWriteException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Handle duplicate username/email (Mongo unique index)
    @ExceptionHandler({ DuplicateKeyException.class, MongoWriteException.class })
    public ResponseEntity<ErrorResponse> handleDuplicate(Exception ex) {

        // Mongo duplicate key error code = 11000
        if (ex instanceof MongoWriteException mongoEx &&
                mongoEx.getError().getCode() != 11000) {

            log.error("Mongo write error (not duplicate)", ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ErrorResponse(
                            ErrorCode.INTERNAL_ERROR.name(),
                            "Database error occurred"
                    ));
        }

        log.warn("Duplicate user detected");

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(
                        ErrorCode.USER_ALREADY_EXISTS.name(),
                        "User already exists. Please try a different username or email."
                ));
    }

    // ✅ Missing or malformed request body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestBody(
            HttpMessageNotReadableException ex) {

        log.error("Invalid or missing request body", ex);

        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(
                        ErrorCode.INVALID_REQUEST.name(),
                        "Invalid or missing request data"
                ));
    }

    // ✅ Fallback for ALL other errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        log.error("Unexpected error occurred", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(
                        ErrorCode.INTERNAL_ERROR.name(),
                        "Something went wrong. Please try again later."
                ));
    }
}
