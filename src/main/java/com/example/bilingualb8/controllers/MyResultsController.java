package com.example.bilingualb8.controllers;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.userResult.MyResultResponse;
import com.example.bilingualb8.services.MyResultService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
@CrossOrigin
public class MyResultsController {

    private final MyResultService myResultService;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "This is get all my result method")
    @GetMapping("/{userId}")
    public List<MyResultResponse> getAll(@PathVariable Long userId) {
        return myResultService.getAll(userId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is delete by id my test method")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return myResultService.deleteById(id);
    }
}
