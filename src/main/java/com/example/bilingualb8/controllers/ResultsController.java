package com.example.bilingualb8.controllers;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.result.EvaluatingSubmittedResultResponse;
import com.example.bilingualb8.dto.responses.result.SubmittedResultsResponse;
import com.example.bilingualb8.dto.responses.userResult.MyResultResponse;
import com.example.bilingualb8.services.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
@CrossOrigin
public class ResultsController {

    private final ResultService resultService;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "This is get all my result method")
    @GetMapping("/myResult")
    public List<MyResultResponse> getAll(Authentication authentication) {
        return resultService.getAll(authentication);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is delete by id my test method")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return resultService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is get all submitted results method")
    @GetMapping("")
    public List<SubmittedResultsResponse> getAll() {
        return resultService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is get evaluating submitted results method")
    @GetMapping("/{resultId}")
    public EvaluatingSubmittedResultResponse get(@PathVariable Long resultId) {
        return resultService.getByIdEvaluatedSubmittedResult(resultId);
    }

}
