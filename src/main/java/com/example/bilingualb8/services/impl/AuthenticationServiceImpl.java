package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.config.security.JwtService;
import com.example.bilingualb8.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualb8.dto.requests.auth.SignUpRequest;
import com.example.bilingualb8.dto.responses.auth.AuthenticationResponse;
import com.example.bilingualb8.entity.User;
import com.example.bilingualb8.entity.UserInfo;
import com.example.bilingualb8.enums.Role;
import com.example.bilingualb8.repositories.UserRepository;
import com.example.bilingualb8.services.AuthenticationService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
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

    @PostConstruct
    public void init() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("admin@gmail.com");
        userInfo.setPassword(passwordEncoder.encode("admin"));
        userInfo.setRole(Role.ADMIN);
        User user = new User();
        user.setFirstName("admin");
        user.setLastName("adminov");
        user.setUserInfo(userInfo);
        user.setIsActive(false);
        userInfo.setUser(user);
        if (userRepository.findUserInfoByEmail(userInfo.getEmail()).isEmpty()) {
            userRepository.save(user);
        }
    }

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        var user = userRepository.findUserInfoByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User was not found."));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
