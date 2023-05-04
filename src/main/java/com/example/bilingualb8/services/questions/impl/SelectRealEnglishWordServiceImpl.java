package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import com.example.bilingualb8.dto.requests.questions.select_real_english.SelectRealEnglishWordRequest;
import com.example.bilingualb8.dto.requests.questions.select_real_english.SelectRealEnglishWordUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Option;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.enums.OptionType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.SelectRealEnglishWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SelectRealEnglishWordServiceImpl implements SelectRealEnglishWordService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    @Override
    public SimpleResponse saveSelectRealEnglishWordQuestion(SelectRealEnglishWordRequest request) {

        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with id : %s doesn't exist !", request.getTestId())));
        Question question = Question.builder()
                .title(request.getTitle())
                .duration(request.getDuration())
                .questionOrder(request.getQuestionOrder())
                .test(test)
                .questionType(QuestionType.SELECT_ENGLISH_WORD)
                .optionType(OptionType.MULTIPLE)
                .build();
        List<Option> options = new ArrayList<>();
        for (OptionRequest optionRequest : request.getOption()) {
            Option option = new Option();
            option.setTitle(optionRequest.getTitle());
            option.setIsCorrect(optionRequest.getIsCorrect() != null ? optionRequest.getIsCorrect() : false);
            option.setFileUrl(optionRequest.getFileUrl());
            option.setQuestion(question);
            options.add(option);
        }
        question.setOptions(options);
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateSelectRealEnglishWordQuestion(Long id, SelectRealEnglishWordUpdateRequest request) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with id : %s doesn't exist! ", id)));

        question.setTitle(request.getTitle() != null ? request.getTitle() : question.getTitle());
        question.setDuration(request.getDuration() != null ? request.getDuration() : question.getDuration());
        question.setQuestionOrder(request.getQuestionOrder() != null ? request.getQuestionOrder() : question.getQuestionOrder());
        List<Option> options = new ArrayList<>();
        if (request.getOption() != null) {
            for (OptionRequest optionRequest : request.getOption()) {
                Option option = new Option();
                option.setTitle(optionRequest.getTitle() != null ? optionRequest.getTitle() : option.getTitle());
                option.setIsCorrect(optionRequest.getIsCorrect() != null ? optionRequest.getIsCorrect() : false);
                option.setFileUrl(optionRequest.getFileUrl() != null ? optionRequest.getFileUrl() : option.getFileUrl());
                options.add(option);
            }
            question.setOptions(options);
        }
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully updated !", id))
                .build();
    }
}
