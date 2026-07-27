package com.example.auth.auth_app_backend.exceptions;

public record ErrorResponse(
        String message,
        String status,
        int statusCode
) {
}
