package com.example.bilingualb8.controllers;

import com.example.bilingualb8.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualb8.dto.requests.auth.ForgotPassword;
import com.example.bilingualb8.dto.requests.auth.ResetPasswordRequest;
import com.example.bilingualb8.dto.requests.auth.SignUpRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.auth.AuthenticationResponse;
import com.example.bilingualb8.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import com.google.firebase.auth.FirebaseAuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "This is sign-up method")
    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    } 

    @Operation(summary = "This is sign-in method")
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.signIn(authenticationRequest));
    }

    @Operation(summary = "This is forgot-password method")
    @PostMapping("/forgot-password")
    public ResponseEntity<SimpleResponse> processForgotPasswordForm(@RequestBody @Valid ForgotPassword forgotPassword) {
        return ResponseEntity.ok(authenticationService.forgotPassword(forgotPassword));
    }

    @Operation(summary = "This is reset-password method")
    @GetMapping("/reset-password")
    public ResponseEntity<SimpleResponse> resetPassword(@RequestParam String token, @RequestBody @Valid ResetPasswordRequest request) {
        return ResponseEntity.ok(authenticationService.resetPassword(token, request.newPassword()));
    }

    @Operation(summary = "This is auth with google method")
    @PostMapping("/auth-google")
    public AuthenticationResponse authWithGoogle(String tokenId) throws FirebaseAuthException {
        return authenticationService.authWithGoogle(tokenId);
    }
}
