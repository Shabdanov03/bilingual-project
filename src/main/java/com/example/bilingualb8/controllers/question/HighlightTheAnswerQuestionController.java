package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.services.questions.HighlightTheAnswerQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/questions/highlight-the-answer")
@RequiredArgsConstructor
@CrossOrigin
public class HighlightTheAnswerQuestionController {
    private final HighlightTheAnswerQuestionService questionService;

    @Operation(summary = "This is save Highlight the answer question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveHighlightTheAnswerQuestion(@RequestBody @Valid HighlightTheAnswerQuestionRequest questionRequest) {
        return questionService.saveHighlightTheAnswerQuestion(questionRequest);
    }

    @Operation(summary = "This is update by id Highlight the answer question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/update")
    public SimpleResponse updateHighlightTheAnswerQuestionById(@PathVariable Long id, @RequestBody
    @Valid HighlightTheAnswerQuestionUpdateRequest updateRequest) {
        return questionService.updateHighlightTheAnswerQuestionById(id, updateRequest);
    }

}
