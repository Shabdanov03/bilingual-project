package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.*;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.TypeWhatYouHearQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypeWhatYouHearQuestionServiceImpl implements TypeWhatYouHearQuestionService {
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;

    @Override
    public SimpleResponse saveTypeWhatYouHearQuestion(TypeWhatYouHearQuestionRequest request) {
        log.info("Saving Type What You Hear question: {}", request.getTitle());
        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new NoSuchElementException(String.format("Test with ID %d not found", request.getTestId())));

        Question question = Question.builder()
                .title(request.getTitle())
                .duration(request.getDuration())
                .questionType(QuestionType.TYPE_WHAT_YOU_HEAR)
                .numberOfReplays(request.getNumberOfReplays())
                .correctAnswer(request.getCorrectAnswer())
                .questionOrder(request.getQuestionOrder())
                .test(test)
                .isActive(request.getIsActive())
                .build();

        File file = new File(FileType.AUDIO, request.getFileRequest(), question);
        question.setFiles(new ArrayList<>(List.of(file)));
        questionRepository.save(question);

        log.info("Question with title '{}' successfully saved", request.getTitle());
        return SimpleResponse.builder()
                .message(String.format("Question with title '%s' successfully saved!", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateTypeWhatYouHear(Long id, TypeWhatYouHearQuestionUpdateRequest updateQuestionRequest) {
        log.info("Updating Type What You Hear question: {}", updateQuestionRequest.getTitle());
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with ID %s doesn't exist!", id)));

        question.setTitle(updateQuestionRequest.getTitle() != null ? updateQuestionRequest.getTitle() : question.getTitle());
        question.setDuration(updateQuestionRequest.getDuration() != null ? updateQuestionRequest.getDuration() : question.getDuration());
        question.setNumberOfReplays(updateQuestionRequest.getNumberOfReplays() != null ? updateQuestionRequest.getNumberOfReplays() : question.getNumberOfReplays());
        question.setCorrectAnswer(updateQuestionRequest.getCorrectAnswer() != null ? updateQuestionRequest.getCorrectAnswer() : question.getCorrectAnswer());
        question.setIsActive(updateQuestionRequest.getIsActive() != null ? updateQuestionRequest.getIsActive() : question.getIsActive());

        if (updateQuestionRequest.getFile() != null) {
            File file = question.getFiles().get(0);
            file.setFileUrl(updateQuestionRequest.getFile());
            question.setFiles(new ArrayList<>(List.of(file)));
        }
        questionRepository.save(question);

        log.info("Question with ID {} successfully updated", id);
        return SimpleResponse.builder()
                .message(String.format("Question with ID %s successfully updated!", id))
                .build();
    }
}
