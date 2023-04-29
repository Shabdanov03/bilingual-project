package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.questions.record_saying_statement.RecordSayingStatementQuestionResponse;
import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;

import java.util.List;
import java.util.Optional;

public interface CustomRecordSayingStatementQuestionRepository {
    List<RecordSayingStatementQuestionResponse> getAllRecordSayingStatementQuestion();
    Optional<RecordSayingStatementQuestionResponse> getRecordSayingStatementQuestionById(Long id);
}
