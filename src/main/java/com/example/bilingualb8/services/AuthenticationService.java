package com.example.bilingualb8.services;

import com.example.bilingualb8.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualb8.dto.requests.auth.ForgotPassword;
import com.example.bilingualb8.dto.requests.auth.SignUpRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.auth.AuthenticationResponse;
import com.google.firebase.auth.FirebaseAuthException;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(AuthenticationRequest authenticationRequest);
    SimpleResponse forgotPassword(ForgotPassword forgotPassword);
    SimpleResponse resetPassword(String token,String newPassword);
    AuthenticationResponse authWithGoogle(String tokenId) throws FirebaseAuthException;

}
