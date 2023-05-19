package com.example.bilingualb8.controllers;

import com.example.bilingualb8.dto.requests.test.PassTestRequest;
import com.example.bilingualb8.dto.requests.test.TestRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.test.TestResponse;
import com.example.bilingualb8.services.TestService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@CrossOrigin
public class TestController {
    private final TestService testService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "This is get all test method")
    @GetMapping
    public List<TestResponse> getAll() {
        return testService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "This is get by id test method")
    @GetMapping("/{id}")
    public TestResponse getById(@PathVariable Long id) {
        return testService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is delete by id test method")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return testService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is update test method")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable @Valid Long id, @RequestBody TestRequest request) {
        return testService.update(id, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is save test method")
    @PostMapping
    public SimpleResponse save(@RequestBody @Valid TestRequest request) {
        return testService.save(request);
    }


    // TODO PASS TEST
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This submit test method")
    @PostMapping("/submit")
    public SimpleResponse submitTest(@RequestBody @Valid PassTestRequest request){
        return testService.submitTest(request);
    }
}