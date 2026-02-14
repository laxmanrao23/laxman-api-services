package com.laxman.job.server.cart.dto;

import com.laxman.job.server.cart.model.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;

    // ✅ success with data
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, null, data);
    }

    // ✅ success without data
    public static ApiResponse<Void> success() {
        return new ApiResponse<>(true, null, null, null);
    }

    // ✅ failure
    public static ApiResponse<Void> failure(ErrorCode errorCode) {
        return new ApiResponse<>(
                false,
                errorCode.name(),
                errorCode.getMessage(),
                null
        );
    }
}


