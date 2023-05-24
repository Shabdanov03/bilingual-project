package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.*;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.DescribeImageQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DescribeImageQuestionServiceImpl implements DescribeImageQuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    public SimpleResponse saveDescribeQuestion(DescribeImageQuestionRequest request) {
        log.info("Saving describe image question");
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with ID %s does not exist!", request.getTestId())));

        Question question = Question.builder()
                .title(request.getTitle())
                .correctAnswer(request.getCorrectAnswer())
                .questionType(QuestionType.DESCRIBE_IMAGE)
                .duration(request.getDuration())
                .test(test)
                .isActive(request.getIsActive())
                .build();

        File file = new File(FileType.IMAGE, request.getFile(), question);
        question.setFiles(List.of(file));
        questionRepository.save(question);

        log.info("Describe image question saved successfully");
        return SimpleResponse.builder()
                .message(String.format("Question with title \"%s\" saved successfully!", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateDescribeImageQuestionById(Long id, DescribeImageQuestionUpdateRequest updateRequest) {
        log.info("Updating describe image question with ID: {}", id);
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with ID %s does not exist!", id)));

        // Update question fields if provided in the update request
        if (updateRequest.getTitle() != null) {
            question.setTitle(updateRequest.getTitle());
        }
        if (updateRequest.getCorrectAnswer() != null) {
            question.setCorrectAnswer(updateRequest.getCorrectAnswer());
        }
        if (updateRequest.getDuration() != null) {
            question.setDuration(updateRequest.getDuration());
        }
        if (updateRequest.getQuestionOrder() != null) {
            question.setQuestionOrder(updateRequest.getQuestionOrder());
        }
        if (updateRequest.getIsActive() != null) {
            question.setIsActive(updateRequest.getIsActive());
        }

        // Update file URL if provided in the update request
        if (updateRequest.getFile() != null) {
            File file = question.getFiles().get(0);
            file.setFileUrl(updateRequest.getFile());
            question.setFiles(List.of(file));
        }

        questionRepository.save(question);

        log.info("Describe image question with ID {} updated successfully", id);
        return SimpleResponse.builder()
                .message(String.format("Question with ID %s updated successfully!", id))
                .build();
    }
}