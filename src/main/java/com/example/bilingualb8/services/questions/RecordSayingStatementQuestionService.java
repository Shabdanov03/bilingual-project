package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.record_saying_statement.RecordSayingStatementQuestionResponse;
import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;

import java.util.List;

public interface RecordSayingStatementQuestionService {
    SimpleResponse saveRecordSayingStatement(RecordSayingStatementQuestionRequest request);

    List<RecordSayingStatementQuestionResponse> getAllRecordSayingStatementQuestion();

    RecordSayingStatementQuestionResponse getRecordSayingStatementQuestion(Long id);

    SimpleResponse deleteRecordSayingStatementQuestionById(Long id);

    SimpleResponse updateRecordSayingStatementQuestion(Long id, RecordSayingStatementQuestionUpdateRequest updateRequest);
}
