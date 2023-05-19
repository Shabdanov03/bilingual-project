package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.select_best_title.SelectBestTitleQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.select_best_title.SelectBestTitleQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.services.questions.SelectBestTitleQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/select-best-title")
@RequiredArgsConstructor
public class SelectBestTitleQuestionController {
    private final SelectBestTitleQuestionService questionService;

    @Operation(summary = "This is save Select-Best-Title question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse save(@RequestBody @Valid SelectBestTitleQuestionRequest request) {
        return questionService.save(request);
    }

    @Operation(summary = "This is update by id Select-Best-Title question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody @Valid SelectBestTitleQuestionUpdateRequest request) {
        return questionService.update(id, request);
    }
}
