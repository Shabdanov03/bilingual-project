package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.type_what_you_hear.TypeWhatYouHearQuestionResponse;
import com.example.bilingualb8.services.questions.TypeWhatYouHearQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/type-what-you-hear")
@RequiredArgsConstructor
@CrossOrigin
public class TypeWhatYouHearQuestionController {
    private final TypeWhatYouHearQuestionService questionService;
    @Operation(summary = "This is save Type what you hear question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public SimpleResponse saveTypeWhatYouHearQuestion(@RequestBody @Valid TypeWhatYouHearQuestionRequest request) {
        return questionService.saveTypeWhatYouHearQuestion(request);
    }
    @Operation(summary = "This is get all Type what you hear question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/all")
    public List<TypeWhatYouHearQuestionResponse> getAllTypeWhatYouHearQuestions() {
        return questionService.getAllTypeWhatYouHearQuestion();
    }
    @Operation(summary = "This is get by id Type what you hear question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public TypeWhatYouHearQuestionResponse getTypeWhatYouHearQuestionById(@PathVariable Long id) {
        return questionService.getTypeWhatYouHearQuestionById(id);
    }
    @Operation(summary = "This is delete by id Type what you hear question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteTypeWhatYouHearQuestion(@PathVariable Long id) {
        return questionService.deleteTypeWhatYouHearById(id);
    }
    @Operation(summary = "This is update by id Type what you hear question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/update")
    public SimpleResponse updateTypeWhatYouHearQuestion(@PathVariable Long id, @RequestBody @Valid
    TypeWhatYouHearQuestionUpdateRequest updateRequest) {
        return questionService.updateTypeWhatYouHear(id,updateRequest);
    }
}
