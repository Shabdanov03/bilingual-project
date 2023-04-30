package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.describe_image.DescribeImageQuestionResponse;
import com.example.bilingualb8.entity.File;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.repositories.custom.CustomDescribeImageQuestionRepository;
import com.example.bilingualb8.services.questions.DescribeImageQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DescribeImageQuestionServiceImpl implements DescribeImageQuestionService {
    private final QuestionRepository questionRepository;
    private final CustomDescribeImageQuestionRepository customRepository;
    private final TestRepository testRepository;

    public SimpleResponse saveDescribeQuestion(DescribeImageQuestionRequest request) {
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with id : %s doesn't exist !", request.getTestId())));
        Question question = Question.builder()
                .title(request.getTitle())
                .correctAnswer(request.getCorrectAnswer())
                .questionType(QuestionType.DESCRIBE_IMAGE)
                .duration(request.getDuration())
                .test(test)
                .build();

        File file = new File(request.getFile().getFileType(), request.getFile().getFileUrl(), question);
        question.setFiles(new ArrayList<>((List.of(file))));
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Override
    public List<DescribeImageQuestionResponse> getAllDescribeImageQuestion() {
        return customRepository.getAllDescribeImageQuestion();
    }

    @Override
    public DescribeImageQuestionResponse getDescribeImageQuestionById(Long id) {
        return customRepository.getDescribeImageQuestionById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with id : %s doesn't exist !", id)));
    }

    @Override
    public SimpleResponse deleteDescribeImageQuestionById(Long id) {
        if (!questionRepository.existsById(id)) {
            return SimpleResponse.builder()
                    .message(String.format("Question with id : %s doesn't exist !", id))
                    .build();
        }
        questionRepository.deleteById(id);
        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully deleted !", id))
                .build();
    }

    @Override
    public SimpleResponse updateDescribeImageQuestionById(Long id, DescribeImageQuestionUpdateRequest updateRequest) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with id : %s doesn't exist! ", id)));

        question.setTitle(updateRequest.getTitle() != null ? updateRequest.getTitle() : question.getTitle());
        question.setCorrectAnswer(updateRequest.getCorrectAnswer() != null ? updateRequest.getCorrectAnswer() : question.getCorrectAnswer());
        question.setDuration(updateRequest.getDuration() != null ? updateRequest.getDuration() : question.getDuration());
        question.setQuestionOrder(updateRequest.getQuestionOrder() != null ? updateRequest.getQuestionOrder() : question.getQuestionOrder());

        if (updateRequest.getFile() != null) {
            File file = question.getFiles().get(0);
            file.setFileUrl(updateRequest.getFile().getFileUrl());
            file.setFileType(updateRequest.getFile().getFileType());
            question.setFiles(new ArrayList<>(List.of(file)));
        }
        questionRepository.save(question);

        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully updated !", id))
                .build();
    }
}
