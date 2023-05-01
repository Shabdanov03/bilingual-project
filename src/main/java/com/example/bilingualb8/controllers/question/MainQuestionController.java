package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.responses.questions.MainQuestionMiniResponse;
import com.example.bilingualb8.dto.responses.questions.MainQuestionResponse;
import com.example.bilingualb8.services.questions.MainQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin
public class MainQuestionController {
    private final MainQuestionService mainQuestionService;
    @Operation(summary = "This is get all Main Question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/all")
    public List<MainQuestionMiniResponse> getAllMainQuestion(){
        return mainQuestionService.getAllQuestions();
    }

    @Operation(summary = "This is get by id Main Question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public MainQuestionResponse getMainQuestionById(@PathVariable Long id){
        return mainQuestionService.getQuestionById(id);
    }
}
