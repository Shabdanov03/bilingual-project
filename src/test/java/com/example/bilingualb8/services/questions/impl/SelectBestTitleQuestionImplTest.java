package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import com.example.bilingualb8.dto.requests.questions.select_best_title.SelectBestTitleQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.select_best_title.SelectBestTitleQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Option;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.services.questions.SelectBestTitleQuestionTest;
import com.example.bilingualb8.repositories.OptionRepository;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.impl.SelectBestTitleQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SelectBestTitleQuestionImplTest implements SelectBestTitleQuestionTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private TestRepository testRepository;
    @Mock
    private OptionRepository optionRepository;
    @InjectMocks
    private SelectBestTitleQuestionServiceImpl selectBestTitleQuestionService;

    @BeforeEach
    void setUp() {
        selectBestTitleQuestionService = new SelectBestTitleQuestionServiceImpl(
                questionRepository, testRepository, optionRepository
        );
    }

    @Override
    @Test
    public void save() {
        SelectBestTitleQuestionRequest request =
                new SelectBestTitleQuestionRequest();
        request.setTitle("Test Title");
        request.setTestId(1L);
        request.setDuration(30);
        request.setPassage("Test Passage");
        request.setQuestionOrder(1);
        request.setIsActive(true);

        // creating a new option request
        OptionRequest optionRequest = OptionRequest
                .builder()
                .title("Test Option 1")
                .fileUrl("Test Option File Url")
                .optionOrder(1)
                .isCorrect(false)
                .build();

        request.setOptions(new ArrayList<>(List.of(optionRequest)));

        com.example.bilingualb8.entity.Test test =
                new com.example.bilingualb8.entity.Test();
        when(testRepository.findById(request.getTestId()))
                .thenReturn(Optional.of(test));


        SimpleResponse response = selectBestTitleQuestionService.save(request);
        assertNotNull(response);

        verify(questionRepository, times(1))
                .save(any(Question.class));
    }

    @Override
    @Test
    public void update() {
        SelectBestTitleQuestionUpdateRequest request =
                new SelectBestTitleQuestionUpdateRequest();
        request.setTitle("Test Title");
        request.setDuration(30);
        request.setPassage("Test Passage");
        request.setQuestionOrder(1);
        request.setIsActive(true);

        // creating a new option request
        OptionUpdateRequest optionRequest = OptionUpdateRequest
                .builder()
                .title("Test Option 1")
                .fileUrl("Test Option File Url")
                .optionOrder(1)
                .isCorrect(false)
                .build();

        request.setOptions(new ArrayList<>(List.of(optionRequest)));


        Question question = new Question();
        question.setId(1L);
        question.setDuration(30);
        question.setTitle("Test Question Title");
        Option option = new Option();
        option.setQuestion(question);
        option.setTitle("Test option title");
        option.setOptionOrder(1);
        option.setIsCorrect(false);
        option.setFileUrl("Test Option URL");
        question.setOptions(new ArrayList<>(List.of(option)));
        question.setPassage("Test Passage");


        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));



        SimpleResponse response = selectBestTitleQuestionService.update(1L,request);
        assertNotNull(response);

        verify(questionRepository, times(1))
                .save(question);

    }
}
