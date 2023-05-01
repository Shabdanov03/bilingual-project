package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.highlight_the_answer.HighlightTheAnswerQuestionResponse;
import com.example.bilingualb8.services.questions.HighlightTheAnswerQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/highlight-the-answer")
@RequiredArgsConstructor
@CrossOrigin
public class HighlightTheAnswerQuestionController {
    private final HighlightTheAnswerQuestionService questionService;
    @Operation(summary = "This is save Highlight the answer question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public SimpleResponse saveHighlightTheAnswerQuestion(@RequestBody @Valid HighlightTheAnswerQuestionRequest questionRequest) {
        return questionService.saveHighlightTheAnswerQuestion(questionRequest);
    }
    @Operation(summary = "This is get all Highlight the answer question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/all")
    public List<HighlightTheAnswerQuestionResponse> getAllHighlightTheAnswerQuestion(){
        return questionService.getAllHighlightTheAnswerQuestion();
    }
    @Operation(summary = "This is get by id Highlight the answer question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public HighlightTheAnswerQuestionResponse getHighlightTheAnswerQuestionById(@PathVariable Long id){
        return questionService.getHighlightTheAnswerQuestionById(id);
    }
    @Operation(summary = "This is delete by id Highlight the answer question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteHighlightTheAnswerQuestionById(@PathVariable Long id){
        return questionService.deleteHighlightTheAnswerQuestionById(id);
    }
    @Operation(summary = "This is update by id Highlight the answer question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/update")
    public SimpleResponse updateHighlightTheAnswerQuestionById(@PathVariable Long id, @RequestBody
    @Valid HighlightTheAnswerQuestionUpdateRequest updateRequest){
        return questionService.updateHighlightTheAnswerQuestionById(id,updateRequest);
    }

}
