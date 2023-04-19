package com.example.bilingualb8.dto.responses.auth;

import com.example.bilingualb8.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        String email,
        Role role
) {
}
