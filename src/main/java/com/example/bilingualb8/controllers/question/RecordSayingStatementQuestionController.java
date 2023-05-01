package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.record_saying_statement.RecordSayingStatementQuestionResponse;
import com.example.bilingualb8.services.questions.RecordSayingStatementQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions/record-saying-statement")
@RequiredArgsConstructor
@CrossOrigin
public class RecordSayingStatementQuestionController {
    private final RecordSayingStatementQuestionService questionService;
    @Operation(summary = "This is save Record saying statement question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public SimpleResponse saveRecordSayingStatementQuestion(@RequestBody @Valid RecordSayingStatementQuestionRequest request) {
        return questionService.saveRecordSayingStatement(request);
    }
    @Operation(summary = "This is get all Record saying statement question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/all")
    public List<RecordSayingStatementQuestionResponse> getAllRecordSayingStatementQuestions() {
        return questionService.getAllRecordSayingStatementQuestion();
    }
    @Operation(summary = "This is get by id Record saying statement question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public RecordSayingStatementQuestionResponse getRecordSayingStatementQuestionById(@PathVariable Long id) {
        return questionService.getRecordSayingStatementQuestion(id);
    }
    @Operation(summary = "This is delete by id Record saying statement question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteRecordSayingStatementQuestion(@PathVariable Long id) {
        return questionService.deleteRecordSayingStatementQuestionById(id);
    }
    @Operation(summary = "This is update by id Record saying statement question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/update")
    public SimpleResponse updateRespondNWordsQuestionById(@PathVariable Long id, @RequestBody @Valid
    RecordSayingStatementQuestionUpdateRequest updateRequest) {
        return questionService.updateRecordSayingStatementQuestion(id,updateRequest);
    }
}
