package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.highlight_the_answer.HighlightTheAnswerQuestionResponse;
import com.example.bilingualb8.services.questions.HighlightTheAnswerQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/highlight-the-answer")
@RequiredArgsConstructor
public class HighlightTheAnswerQuestionController {
    private final HighlightTheAnswerQuestionService questionService;
    @PostMapping("/save")
    public SimpleResponse saveHighlightTheAnswerQuestion(@RequestBody @Valid HighlightTheAnswerQuestionRequest questionRequest) {
        return questionService.saveHighlightTheAnswerQuestion(questionRequest);
    }
    @GetMapping("/all")
    public List<HighlightTheAnswerQuestionResponse> getAllHighlightTheAnswerQuestion(){
        return questionService.getAllHighlightTheAnswerQuestion();
    }
    @GetMapping("/{id}")
    public HighlightTheAnswerQuestionResponse getHighlightTheAnswerQuestionById(@PathVariable Long id){
        return questionService.getHighlightTheAnswerQuestionById(id);
    }
    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteHighlightTheAnswerQuestionById(@PathVariable Long id){
        return questionService.deleteHighlightTheAnswerQuestionById(id);
    }
    @PutMapping("/{id}/update")
    public SimpleResponse updateHighlightTheAnswerQuestionById(@PathVariable Long id, @RequestBody
    @Valid HighlightTheAnswerQuestionUpdateRequest updateRequest){
        return questionService.updateHighlightTheAnswerQuestionById(id,updateRequest);
    }

}
