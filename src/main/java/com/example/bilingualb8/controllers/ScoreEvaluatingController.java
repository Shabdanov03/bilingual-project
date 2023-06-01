package com.example.bilingualb8.controllers;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.services.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluating")
@RequiredArgsConstructor
@CrossOrigin
public class ScoreEvaluatingController {
    private final AnswerService answerService;

    @PostMapping("/{answerId}")
    @Operation(summary = "This is evaluating score method")
    public SimpleResponse evaluating(@PathVariable Long answerId, @RequestBody float score){
        return answerService.evaluating(answerId, score);
    }

}
