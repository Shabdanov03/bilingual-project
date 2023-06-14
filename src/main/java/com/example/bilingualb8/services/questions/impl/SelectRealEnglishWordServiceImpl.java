package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import com.example.bilingualb8.dto.requests.questions.select_real_english.SelectRealEnglishWordRequest;
import com.example.bilingualb8.dto.requests.questions.select_real_english.SelectRealEnglishWordUpdateRequest;
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
import com.example.bilingualb8.services.questions.SelectRealEnglishWordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SelectRealEnglishWordServiceImpl implements SelectRealEnglishWordService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final OptionRepository optionRepository;

    @Override
    public SimpleResponse saveSelectRealEnglishWordQuestion(SelectRealEnglishWordRequest request) {
        log.info("Saving Select Real English Word question: {}", request.getTitle());
        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new NotFoundException(String.format("Test with ID %s doesn't exist", request.getTestId())));

        Question question = Question.builder()
                .title(request.getTitle())
                .duration(request.getDuration())
                .questionOrder(request.getQuestionOrder())
                .test(test)
                .questionType(QuestionType.SELECT_ENGLISH_WORD)
                .optionType(OptionType.MULTIPLE)
                .isActive(request.getIsActive())
                .build();

        List<Option> options = new ArrayList<>();
        for (OptionRequest optionRequest : request.getOptions()) {
            Option option = new Option();
            option.setTitle(optionRequest.getTitle());
            option.setIsCorrect(optionRequest.getIsCorrect() != null ? optionRequest.getIsCorrect() : false);
            option.setOptionOrder(optionRequest.getOptionOrder());
            option.setQuestion(question);
            options.add(option);
        }
        question.setOptions(options);
        questionRepository.save(question);

        log.info("Question with title '{}' successfully saved", request.getTitle());
        return SimpleResponse.builder()
                .message(String.format("Question with title '%s' successfully saved!", request.getTitle()))
                .build();
    }

    @Transactional
    @Override
    public SimpleResponse updateSelectRealEnglishWordQuestion(Long id, SelectRealEnglishWordUpdateRequest request) {
        log.info("Updating Select Real English Word question: {}", request.getTitle());
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with ID %s doesn't exist", id)));

        if (request.getTitle() != null) {
            question.setTitle(request.getTitle());
        }
        if (request.getDuration() != null) {
            question.setDuration(request.getDuration());
        }
        if (request.getQuestionOrder() != null) {
            question.setQuestionOrder(request.getQuestionOrder());
        }
        if (request.getIsActive() != null){
            question.setIsActive(request.getIsActive());
        }

        List<Option> options = question.getOptions(); // Existing options
        List<OptionUpdateRequest> requestOptions = request.getOptions(); // Request options

        Map<Long, Option> optionMap = new HashMap<>();
        for (Option option : options) {
            optionMap.put(option.getId(), option);
        }

        for (OptionUpdateRequest optionRequest : requestOptions) {
            Long optionId = optionRequest.getId();
            Option option = optionMap.get(optionId);

            if (option == null) {
                // Create a new option
                option = new Option();
                option.setTitle(optionRequest.getTitle());
                option.setOptionOrder(optionRequest.getOptionOrder());
                option.setIsCorrect(optionRequest.getIsCorrect() != null ? optionRequest.getIsCorrect() : false);
                option.setQuestion(question);
                options.add(option);
            } else {
                // Update existing option
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

        // Delete options not in the request
        for (Option option : optionMap.values()) {
            optionRepository.delete(option);
            options.remove(option);
        }

        questionRepository.save(question);

        log.info("Question with ID {} successfully updated", id);
        return SimpleResponse.builder()
                .message(String.format("Question with ID %s successfully updated!", id))
                .build();
    }
}