package com.example.bilingualb8.controllers;
import com.example.bilingualb8.dto.requests.auth.AuthenticationRequest;
import com.example.bilingualb8.dto.requests.auth.SignUpRequest;
import com.example.bilingualb8.dto.responses.auth.AuthenticationResponse;
import com.example.bilingualb8.services.AuthenticationService;
import com.example.bilingualb8.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final TestService testService;

    @PostMapping("/auth/sign-up")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.signIn(authenticationRequest));
    }

    @GetMapping("/test")
    public String test(){
        return testService.test();
    }
}
