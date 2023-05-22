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
        log.info("This is save method");
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NoSuchElementException(String.format("Test with id: %s not found", request.getTestId())));
        Question question = Question.builder()
                .title(request.getTitle())
                .duration(request.getDuration())
                .questionOrder(request.getQuestionOrder())
                .numberOfReplays(request.getNumberOfReplays())
                .correctAnswer(request.getCorrectAnswer())
                .questionType(QuestionType.LISTEN_AND_SELECT_ENGLISH_WORD)
                .optionType(OptionType.MULTIPLE)
                .test(test)
                .isActive(request.getIsActive())
                .build();

        List<Option> options = new ArrayList<>();
        for (OptionRequest optionRequest : request.getOptionsRequest()) {
            Option option = new Option();
            option.setTitle(optionRequest.getTitle());
            option.setIsCorrect(optionRequest.getIsCorrect());
            option.setFileUrl(optionRequest.getFileUrl());
            option.setQuestion(question);
            options.add(option);
        }
        question.setOptions(options);
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Listen and select english words Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Transactional
    @Override
    public SimpleResponse update(ListenAndSelectEnglishWordsUpdateRequest request, Long id) {
        log.info("This is update Listen and select English words method");
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with id %s was not found", id)));

        if (request.getTitle() != null) {
            question.setTitle(request.getTitle());
        }
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
                option.setIsCorrect(optionRequest.getIsCorrect() != null ? optionRequest.getIsCorrect() : false);
                option.setQuestion(question);
                options.add(option);
            } else {
                if (optionRequest.getTitle() != null) {
                    option.setTitle(optionRequest.getTitle());
                }
                if (optionRequest.getOptionOrder() != null) {
                    option.setOptionOrder(optionRequest.getOptionOrder());
                }
                if (optionRequest.getIsCorrect() != null) {
                    option.setIsCorrect(optionRequest.getIsCorrect());
                }
                optionMap.remove(optionId);
            }
        }

        for (Option option : optionMap.values()) {
            optionRepository.delete(option);
            options.remove(option);
        }
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with id %s updated successfully!", id))
                .build();
    }
}
