package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.record_saying_statement.RecordSayingStatementQuestionResponse;
import com.example.bilingualb8.services.questions.RecordSayingStatementQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions/record-saying-statement")
@RequiredArgsConstructor
public class RecordSayingStatementQuestionController {
    private final RecordSayingStatementQuestionService questionService;

    @PostMapping("/save")
    public SimpleResponse saveRecordSayingStatementQuestion(@RequestBody @Valid RecordSayingStatementQuestionRequest request) {
        return questionService.saveRecordSayingStatement(request);
    }

    @GetMapping("/all")
    public List<RecordSayingStatementQuestionResponse> getAllRecordSayingStatementQuestions() {
        return questionService.getAllRecordSayingStatementQuestion();
    }

    @GetMapping("/{id}")
    public RecordSayingStatementQuestionResponse getRecordSayingStatementQuestionById(@PathVariable Long id) {
        return questionService.getRecordSayingStatementQuestion(id);
    }

    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteRecordSayingStatementQuestion(@PathVariable Long id) {
        return questionService.deleteRecordSayingStatementQuestionById(id);
    }

    @PutMapping("/{id}/update")
    public SimpleResponse updateRespondNWordsQuestionById(@PathVariable Long id, @RequestBody @Valid
    RecordSayingStatementQuestionUpdateRequest updateRequest) {
        return questionService.updateRecordSayingStatementQuestion(id,updateRequest);
    }
}
