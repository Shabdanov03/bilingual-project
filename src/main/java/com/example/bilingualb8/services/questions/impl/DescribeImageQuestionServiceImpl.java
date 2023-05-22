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
        log.info("This is save describe image question method");
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with id : %s doesn't exist !", request.getTestId())));
        Question question = Question.builder()
                .title(request.getTitle())
                .correctAnswer(request.getCorrectAnswer())
                .questionType(QuestionType.DESCRIBE_IMAGE)
                .duration(request.getDuration())
                .test(test)
                .isActive(request.getIsActive())
                .build();
        File file = new File(FileType.IMAGE, request.getFile(), question);
        question.setFiles(new ArrayList<>((List.of(file))));
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateDescribeImageQuestionById(Long id, DescribeImageQuestionUpdateRequest updateRequest) {
        log.info("This is update Describe image question method");
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with id : %s doesn't exist! ", id)));

        question.setTitle(updateRequest.getTitle() != null ? updateRequest.getTitle() : question.getTitle());
        question.setCorrectAnswer(updateRequest.getCorrectAnswer() != null ? updateRequest.getCorrectAnswer() : question.getCorrectAnswer());
        question.setDuration(updateRequest.getDuration() != null ? updateRequest.getDuration() : question.getDuration());
        question.setQuestionOrder(updateRequest.getQuestionOrder() != null ? updateRequest.getQuestionOrder() : question.getQuestionOrder());
        question.setIsActive(updateRequest.getIsActive() != null ? updateRequest.getIsActive() : question.getIsActive());

        if (updateRequest.getFile() != null) {
            File file = question.getFiles().get(0);
            file.setFileUrl(updateRequest.getFile());
            question.setFiles(new ArrayList<>(List.of(file)));
        }
        questionRepository.save(question);

        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully updated !", id))
                .build();
    }
}
