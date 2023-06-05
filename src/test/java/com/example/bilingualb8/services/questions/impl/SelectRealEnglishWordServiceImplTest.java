package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import com.example.bilingualb8.dto.requests.questions.select_real_english.SelectRealEnglishWordRequest;
import com.example.bilingualb8.dto.requests.questions.select_real_english.SelectRealEnglishWordUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Option;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.repositories.OptionRepository;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.SelectRealEnglishWordServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SelectRealEnglishWordServiceImplTest implements SelectRealEnglishWordServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private TestRepository testRepository;
    @Mock
    private OptionRepository optionRepository;
    @InjectMocks
    private SelectRealEnglishWordServiceImpl questionService;

    @BeforeEach
    void setUp() {
        questionService = new SelectRealEnglishWordServiceImpl(questionRepository, testRepository, optionRepository);
    }

    @Test
    @Deprecated
    @Override
    public void canSaveSelectRealEnglishWordQuestion() {
        SelectRealEnglishWordRequest request = new SelectRealEnglishWordRequest();
        request.setTestId(1L);
        request.setTitle("Question title");
        request.setDuration(30);
        request.setQuestionOrder(3);
        OptionRequest optionRequest = new OptionRequest();
        optionRequest.setTitle("adsfafad");
        optionRequest.setIsCorrect(false);
        optionRequest.setFileUrl("dsfafads");
        optionRequest.setOptionOrder(3);
        request.setOptions(List.of(optionRequest));

        com.example.bilingualb8.entity.Test test = new com.example.bilingualb8.entity.Test();
        when(testRepository.findById(request.getTestId())).thenReturn(Optional.of(test));

        SimpleResponse response = questionService.saveSelectRealEnglishWordQuestion(request);

        assertNotNull(response);

        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    @Override
    public void canUpdateSelectRealEnglishWordQuestion() {
        Long questionId = 1L;
        Question question = new Question();
        question.setId(questionId);
        question.setTitle("Question title");
        question.setCorrectAnswer("Correct answer");
        question.setDuration(60);
        question.setQuestionOrder(1);
        question.setIsActive(true);
        Option option = new Option(1L, "option", false, question, "dafsfa", 12);
        question.setOptions(List.of(option));
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        SelectRealEnglishWordUpdateRequest updateRequest = new SelectRealEnglishWordUpdateRequest();
        updateRequest.setTitle("Question title");
        updateRequest.setDuration(30);
        updateRequest.setQuestionOrder(3);
        OptionUpdateRequest optionRequest = new OptionUpdateRequest();
        optionRequest.setId(1L);
        optionRequest.setTitle("adsfafad");
        optionRequest.setIsCorrect(false);
        optionRequest.setFileUrl("dsfafads");
        optionRequest.setOptionOrder(3);
        updateRequest.setOptions(List.of(optionRequest));

        SimpleResponse response = questionService.updateSelectRealEnglishWordQuestion(questionId, updateRequest);

        assertNotNull(response);

        verify(questionRepository, times(1)).save(question);

    }
}