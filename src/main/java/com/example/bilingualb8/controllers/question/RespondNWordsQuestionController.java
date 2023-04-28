package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;
import com.example.bilingualb8.services.questions.RespondNWordsQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/questions/respond-n-words")
@RequiredArgsConstructor
public class RespondNWordsQuestionController {
    private final RespondNWordsQuestionService questionService;

    @GetMapping("/all")
    List<RespondNWordsQuestionResponse> getAllRespondNWordsQuestion(){
        return questionService.getAllRespondNWordsQuestion();
    }
}
