package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.File;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;


public class TypeWhatYouHearQuestionServiceImplTest {
    @Mock
    private TestRepository testRepository;
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private TypeWhatYouHearQuestionServiceImpl questionService;

    @BeforeEach
    public void setup() { MockitoAnnotations.openMocks(this);
    }
    @org.junit.jupiter.api.Test
    public void testSaveTypeWhatYouHearQuestion() {
        long testId = 1;
        String title = "Test Question";
        int duration = 10;
        int numberOfReplays = 3;
        String correctAnswer = "Answer";
        int questionOrder = 1;
        boolean isActive = true;
        String fileRequest = "audio.mp3";
        TypeWhatYouHearQuestionRequest request = new TypeWhatYouHearQuestionRequest();
        request.setTestId(testId); request.setTitle(title); request.setDuration(duration);
        request.setNumberOfReplays(numberOfReplays);
        request.setCorrectAnswer(correctAnswer);
        request.setQuestionOrder(questionOrder);
        request.setIsActive(isActive);
        request.setFileRequest(fileRequest);
        Test test = new Test();
        test.setId(testId);
        Mockito.when(testRepository.findById(testId)).thenReturn(Optional.of(test));
        Mockito.when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));
        SimpleResponse response = questionService.saveTypeWhatYouHearQuestion(request);

        Mockito.verify(testRepository).findById(testId);
        Mockito.verify(questionRepository).save(any(Question.class));
    }
    @org.junit.jupiter.api.Test
    public void testUpdateTypeWhatYouHear() {
        long questionId = 1;
        String updatedTitle = "Updated Question";
        Integer updatedDuration = 15;
        Integer updatedNumberOfReplays = 5;
        String updatedCorrectAnswer = "Updated Answer";
        Boolean updatedIsActive = false;
        String updatedFileUrl = "updated_audio.mp3";
        TypeWhatYouHearQuestionUpdateRequest updateRequest = new TypeWhatYouHearQuestionUpdateRequest();
        updateRequest.setTitle(updatedTitle);
        updateRequest.setDuration(updatedDuration);
        updateRequest.setNumberOfReplays(updatedNumberOfReplays);
        updateRequest.setCorrectAnswer(updatedCorrectAnswer);
        updateRequest.setIsActive(updatedIsActive);
        updateRequest.setFile(updatedFileUrl);
        Question existingQuestion = new Question();
        existingQuestion.setId(questionId);
        File existingFile = new File(FileType.AUDIO, "audio.mp3", existingQuestion);
        existingQuestion.setFiles(new ArrayList<>(List.of(existingFile)));
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));
        Mockito.when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));
        SimpleResponse response = questionService.updateTypeWhatYouHear(questionId, updateRequest);
        Mockito.verify(questionRepository).findById(questionId);
        Mockito.verify(questionRepository).save(any(Question.class));
    }

}
