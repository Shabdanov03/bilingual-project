package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.config.jwt.JwtService;
import com.example.bilingualb8.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualb8.dto.requests.auth.ForgotPassword;
import com.example.bilingualb8.dto.requests.auth.SignUpRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.auth.AuthenticationResponse;
import com.example.bilingualb8.entity.User;
import com.example.bilingualb8.entity.UserInfo;
import com.example.bilingualb8.enums.Role;
import com.example.bilingualb8.exceptions.AlreadyExistException;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.UserInfoRepository;
import com.example.bilingualb8.repositories.UserRepository;
import com.example.bilingualb8.services.AuthenticationService;
import com.example.bilingualb8.services.EmailService;
import jakarta.mail.MessagingException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoRepository userInfoRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByUserInfoEmail(signUpRequest.email())) {
            throw new AlreadyExistException("Sorry, this email is already registered. Please try a different email or login to your existing account");
        }
        var newUserInfo = UserInfo.builder()
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(Role.USER)
                .build();

        User newUser = new User();
        newUser.setFirstName(signUpRequest.firstName());
        newUser.setLastName(signUpRequest.lastName());
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
        var user = userRepository.findUserInfoByEmail(authenticationRequest.email())
                .orElseThrow(() -> new NotFoundException("User was not found."));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
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

    @Override
    public SimpleResponse forgotPassword(ForgotPassword forgotPassword) {
        UserInfo userInfo = userRepository.findUserInfoByEmail(forgotPassword.email())
                .orElseThrow(() -> new NotFoundException("User was not found"));
        try {
            String token = UUID.randomUUID().toString();
            userInfo.setResetPasswordToken(token);
            userInfoRepository.save(userInfo);

            String subject = "Password Reset Request";
            String resetPasswordLink = "http://localhost:8080/api/auth/reset-password?token=" + token;

            Context context = new Context();
            context.setVariable("title", "Password Reset");
            context.setVariable("message", "Hello Nuriza");
            context.setVariable("token", resetPasswordLink);
            context.setVariable("tokenTitle", "Reset Password");

            String htmlContent = templateEngine.process("reset-password-template.html", context);

            emailService.sendEmail(forgotPassword.email(), subject, htmlContent);
            return SimpleResponse.builder()
                    .message("The password reset was sent to your email. Please check your email.")
                    .build();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SimpleResponse resetPassword(String token, String newPassword) {
        UserInfo userInfo = userInfoRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new NotFoundException("User was not found"));
        userInfo.setPassword(passwordEncoder.encode(newPassword));
        userInfo.setResetPasswordToken(null);
        userInfoRepository.save(userInfo);
        return SimpleResponse.builder().message("User password changed successfully!").build();
    }


    @Override
    public AuthenticationResponse authWithGoogle(String tokenId) throws FirebaseAuthException {
        FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(tokenId);
        if (userRepository.findUserInfoByEmail(firebaseToken.getEmail()).isEmpty()) {
            User newUser = new User();
            String[] name = firebaseToken.getName().split(" ");
            newUser.setFirstName(name[0]);
            newUser.setLastName(name[1]);
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(firebaseToken.getEmail());
            userInfo.setPassword(firebaseToken.getEmail());
            userInfo.setRole(Role.USER);
            userRepository.save(newUser);
        }
        UserInfo userInfo = userRepository.findUserInfoByEmail(firebaseToken.getEmail()).orElseThrow(() -> {
            log.error(String.format("Пользователь с таким электронным адресом %s не найден!", firebaseToken.getEmail()));
            return new NotFoundException(String.format("Пользователь с таким электронным адресом %s не найден!", firebaseToken.getEmail()));
        });

        String token = jwtService.generateToken(userInfo);
        log.info("successfully works the authorization with google method");
        return AuthenticationResponse.builder()
                .email(firebaseToken.getEmail())
                .token(token)
                .role(userInfo.getRole())
                .build();
    }

    @PostConstruct
    public void init() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("billingual.json").getInputStream());
            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(googleCredentials)
                    .build();
            log.info("successfully works the init method");
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions);
        } catch (IOException e) {
            log.error("IOException");
        }
    }
}
