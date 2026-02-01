package com.laxman.job.server.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.laxman.job.server.dto.ErrorResponse;
import com.mongodb.MongoWriteException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ DuplicateKeyException.class, MongoWriteException.class })
    public ResponseEntity<ErrorResponse> handleDuplicate(Exception ex) {

        log.error("Duplicate user detected", ex);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        ErrorCode.USER_ALREADY_EXISTS.name(),
                        "User already exists. Please try a different username or email."
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        log.error("Unexpected error occurred", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        ErrorCode.INTERNAL_ERROR.name(),
                        "Something went wrong. Please try again later."
                ));
    }
}
