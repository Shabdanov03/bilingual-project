package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface RecordSayingStatementQuestionService {
    SimpleResponse saveRecordSayingStatement(RecordSayingStatementQuestionRequest request);

    SimpleResponse updateRecordSayingStatementQuestion(Long id, RecordSayingStatementQuestionUpdateRequest updateRequest);
}
