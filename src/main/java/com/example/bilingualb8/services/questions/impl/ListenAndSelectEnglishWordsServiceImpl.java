package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import com.example.bilingualb8.dto.requests.questions.listen_and_select_english_words.ListenAndSelectEnglishWordsRequest;
import com.example.bilingualb8.dto.requests.questions.listen_and_select_english_words.ListenAndSelectEnglishWordsUpdateRequest;
import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Option;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.enums.OptionType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.OptionRepository;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.ListenAndSelectEnglishWordsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class ListenAndSelectEnglishWordsServiceImpl implements ListenAndSelectEnglishWordsService {
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    @Override
    public SimpleResponse save(ListenAndSelectEnglishWordsRequest request) {
        log.info("Saving Listen and Select English Words question");
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NoSuchElementException(String.format("Test with ID %s not found", request.getTestId())));

        Question question = Question.builder()
                .title(request.getTitle())
                .duration(request.getDuration())
                .questionOrder(request.getQuestionOrder())
                .questionType(QuestionType.LISTEN_AND_SELECT_ENGLISH_WORD)
                .optionType(OptionType.MULTIPLE)
                .test(test)
                .isActive(request.getIsActive())
                .build();

        List<Option> options = new ArrayList<>();
        for (OptionRequest optionRequest : request.getOptions()) {
            Option option = new Option();
            option.setTitle(optionRequest.getTitle());
            option.setIsCorrect(optionRequest.getIsCorrect());
            option.setOptionOrder(optionRequest.getOptionOrder());
            option.setFileUrl(optionRequest.getFileUrl());
            option.setQuestion(question);
            options.add(option);
        }
        question.setOptions(options);
        questionRepository.save(question);

        log.info("Listen and Select English Words question saved successfully");
        return SimpleResponse.builder()
                .message(String.format("Listen and Select English Words question with title \"%s\" successfully saved!", request.getTitle()))
                .build();
    }

    @Transactional
    @Override
    public SimpleResponse update(ListenAndSelectEnglishWordsUpdateRequest request, Long id) {
        log.info("Updating Listen and Select English Words question with ID: {}", id);
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with ID %s was not found", id)));

        // Update question fields if provided in the update request
        question.setTitle(request.getTitle());
        if (request.getDuration() != null) {
            question.setDuration(request.getDuration());
        }
        if (request.getQuestionOrder() != null) {
            question.setQuestionOrder(request.getQuestionOrder());
        }
        if (request.getCorrectAnswer() != null) {
            question.setCorrectAnswer(request.getCorrectAnswer());
        }
        if (request.getNumberOfReplays() != null) {
            question.setNumberOfReplays(request.getNumberOfReplays());
        }
        if (request.getIsActive() != null) {
            question.setIsActive(request.getIsActive());
        }

        List<Option> options = question.getOptions();
        List<OptionUpdateRequest> requestOptions = request.getOptions();

        Map<Long, Option> optionMap = new HashMap<>();
        for (Option option : options) {
            optionMap.put(option.getId(), option);
        }

        for (OptionUpdateRequest optionRequest : requestOptions) {
            Long optionId = optionRequest.getId();
            Option option = optionMap.get(optionId);

            if (option == null) {
                option = new Option();
                option.setTitle(optionRequest.getTitle());
                option.setOptionOrder(optionRequest.getOptionOrder());
                option.setIsCorrect(optionRequest.getIsCorrect());
                option.setQuestion(question);
                options.add(option);
            } else {
                option.setTitle(optionRequest.getTitle());
                option.setOptionOrder(optionRequest.getOptionOrder());
                option.setIsCorrect(optionRequest.getIsCorrect());
                optionMap.remove(optionId);
            }
        }

        for (Option option : optionMap.values()) {
            optionRepository.delete(option);
            options.remove(option);
        }
        questionRepository.save(question);

        log.info("Listen and Select English Words question with ID {} updated successfully", id);
        return SimpleResponse.builder()
                .message(String.format("Question with ID %s updated successfully!", id))
                .build();
    }
}