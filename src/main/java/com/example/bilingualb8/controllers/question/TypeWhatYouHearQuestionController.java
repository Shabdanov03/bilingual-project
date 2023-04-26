package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.question.TypeWhatYouHearQuestionRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.services.questions.TypeWhatYouHearQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TypeWhatYouHearQuestionController {
    private final TypeWhatYouHearQuestionService service;
    @PostMapping
    public SimpleResponse save (@RequestBody TypeWhatYouHearQuestionRequest request){
        return service.save(request);
    }
}
