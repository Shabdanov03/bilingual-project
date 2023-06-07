package com.example.bilingualb8.services.questions.impl;
import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecordSayingStatementQuestionTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private TestRepository testRepository;

    @InjectMocks
    private RecordSayingStatementQuestionServiceImpl recordSayingStatementQuestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRecordSayingStatement_WithValidRequest_ShouldSaveQuestion() {
        RecordSayingStatementQuestionRequest request = new RecordSayingStatementQuestionRequest();
        request.setTitle("Title");
        request.setStatement("Statement");
        request.setCorrectAnswer("Correct Answer");
        request.setDuration(60);
        request.setQuestionOrder(1);
        request.setTestId(123L);
        request.setIsActive(true);


        com.example.bilingualb8.entity.Test test = new com.example.bilingualb8.entity.Test();
        when(testRepository.findById(request.getTestId())).thenReturn(Optional.of(test));

        SimpleResponse response = recordSayingStatementQuestionService.saveRecordSayingStatement(request);

        verify(testRepository, times(1)).findById(request.getTestId());
        verify(questionRepository, times(1)).save(any(Question.class));

        assertNotNull(response);
        assertEquals("Question with title 'Title' successfully saved", response.message());
    }


    @Test
    void updateRecordSayingStatementQuestion_WithNonExistingQuestion_ShouldThrowNotFoundException() {
        Long questionId = 456L;

        RecordSayingStatementQuestionUpdateRequest updateRequest = new RecordSayingStatementQuestionUpdateRequest();
        updateRequest.setTitle("Updated Title");
        updateRequest.setStatement("Updated Statement");
        updateRequest.setDuration(60);
        updateRequest.setQuestionOrder(2);
        updateRequest.setIsActive(true);

        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                recordSayingStatementQuestionService.updateRecordSayingStatementQuestion(questionId, updateRequest));

        verify(questionRepository, times(1)).findById(questionId);
        verify(questionRepository, never()).save(any(Question.class));
    }
}
