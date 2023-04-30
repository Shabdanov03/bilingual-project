package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.type_what_you_hear.TypeWhatYouHearQuestionResponse;
import com.example.bilingualb8.services.questions.TypeWhatYouHearQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/type-what-you-hear")
@RequiredArgsConstructor
public class TypeWhatYouHearQuestionController {
    private final TypeWhatYouHearQuestionService questionService;


    @PostMapping("/save")
    public SimpleResponse saveTypeWhatYouHearQuestion(@RequestBody @Valid TypeWhatYouHearQuestionRequest request) {
        return questionService.saveTypeWhatYouHearQuestion(request);
    }

    @GetMapping("/all")
    public List<TypeWhatYouHearQuestionResponse> getAllTypeWhatYouHearQuestions() {
        return questionService.getAllTypeWhatYouHearQuestion();
    }

    @GetMapping("/{id}")
    public TypeWhatYouHearQuestionResponse getTypeWhatYouHearQuestionById(@PathVariable Long id) {
        return questionService.getTypeWhatYouHearQuestionById(id);
    }

    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteTypeWhatYouHearQuestion(@PathVariable Long id) {
        return questionService.deleteTypeWhatYouHearById(id);
    }

    @PutMapping("/{id}/update")
    public SimpleResponse updateTypeWhatYouHearQuestion(@PathVariable Long id, @RequestBody @Valid
    TypeWhatYouHearQuestionUpdateRequest updateRequest) {
        return questionService.updateTypeWhatYouHear(id,updateRequest);
    }
}
