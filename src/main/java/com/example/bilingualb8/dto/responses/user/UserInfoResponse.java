package com.example.bilingualb8.dto.responses.user;

import com.example.bilingualb8.enums.Role;
import lombok.Builder;

@Builder
public record UserInfoResponse(
        String email,
        Role role
) {
}
