package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.select_best_title.SelectBestTitleQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.select_best_title.SelectBestTitleQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.services.questions.SelectBestTitleQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/select-best-title")
@RequiredArgsConstructor
public class SelectBestTitleQuestionController {
    private final SelectBestTitleQuestionService questionService;

    @PostMapping
    public SimpleResponse save(@RequestBody @Valid SelectBestTitleQuestionRequest request) {
        return questionService.save(request);
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody @Valid SelectBestTitleQuestionUpdateRequest request){
        return questionService.update(id,request);
    }
}
