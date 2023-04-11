package com.example.bilingualb8.dto.response;

import java.security.SecureRandom;

public record UserInfoResponse(
        String email,
        String password
) {
}
