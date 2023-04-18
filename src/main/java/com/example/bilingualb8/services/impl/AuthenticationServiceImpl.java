package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.config.jwt.JwtService;
import com.example.bilingualb8.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualb8.dto.requests.auth.SignUpRequest;
import com.example.bilingualb8.dto.responses.auth.AuthenticationResponse;
import com.example.bilingualb8.entity.User;
import com.example.bilingualb8.entity.UserInfo;
import com.example.bilingualb8.enums.Role;
import com.example.bilingualb8.exceptions.AlreadyExistException;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.UserRepository;
import com.example.bilingualb8.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByUserInfoEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistException("Sorry, this email is already registered. Please try a different email or login to your existing account");
        }
        var newUserInfo = UserInfo.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();

        User newUser = new User();
        newUser.setFirstName(signUpRequest.getFirstName());
        newUser.setLastName(signUpRequest.getLastName());
        newUser.setIsActive(false);
        newUser.setUserInfo(newUserInfo);
        newUserInfo.setUser(newUser);
        userRepository.save(newUser);

        var jwtToken = jwtService.generateToken(newUserInfo);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(newUserInfo.getEmail())
                .role(newUserInfo.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findUserInfoByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User was not found."));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
