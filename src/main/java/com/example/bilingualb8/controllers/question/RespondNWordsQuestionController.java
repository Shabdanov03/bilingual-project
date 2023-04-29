package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;
import com.example.bilingualb8.services.questions.RespondNWordsQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/respond-n-words")
@RequiredArgsConstructor
public class RespondNWordsQuestionController {
    private final RespondNWordsQuestionService questionService;

    @PostMapping("/save")
    public SimpleResponse saveRespondNWordsQuestion(@RequestBody @Valid RespondNWordsQuestionRequest request) {
        return questionService.saveRespondNWordsQuestion(request);
    }

    @GetMapping("/all")
    public List<RespondNWordsQuestionResponse> getAllRespondNWordsQuestion() {
        return questionService.getAllRespondNWordsQuestion();
    }

    @GetMapping("/{id}")
    public RespondNWordsQuestionResponse getRespondNWordsQuestionById(@PathVariable Long id) {
        return questionService.getRespondNWordsQuestionById(id);
    }

    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteRespondNWordsQuestionById(@PathVariable Long id) {
        return questionService.deleteRespondNWordsQuestionById(id);
    }

    @PutMapping("/{id}/update")
    public SimpleResponse updateRespondNWordsQuestionById(@PathVariable Long id, @RequestBody @Valid
    RespondNWordsQuestionUpdateRequest updateRequest) {
        return questionService.updateRespondNWordsQuestionById(id, updateRequest);
    }
}
