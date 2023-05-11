package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import com.example.bilingualb8.dto.requests.questions.select_the_main_idea.SelectTheMainIdeaQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.select_the_main_idea.SelectTheMainIdeaQuestionUpdateRequest;
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
import com.example.bilingualb8.services.questions.SelectTheMainIdeaQuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SelectTheMainIdeaQuestionServiceImpl implements SelectTheMainIdeaQuestionService {
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    @Override
    public SimpleResponse saveSelectTheMainIdeaQuestion(SelectTheMainIdeaQuestionRequest request) {
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with id : %s doesn't exist !", request.getTestId())));

        Question question = Question.builder()
                .title(request.getTitle())
                .passage(request.getPassage())
                .optionType(OptionType.SINGLE)
                .duration(request.getDuration())
                .questionOrder(request.getQuestionOrder())
                .questionType(QuestionType.SELECT_THE_MAIN_IDEA)
                .test(test)
                .build();
        List<Option> optionList = new ArrayList<>();
        for (OptionRequest optionRequest : request.getOptions()) {
            Option option = new Option();
            option.setTitle(optionRequest.getTitle());
            option.setIsCorrect(optionRequest.getIsCorrect() != null ? optionRequest.getIsCorrect() : false);
            option.setQuestion(question);
            optionList.add(option);

        }
        question.setOptions(optionList);
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Transactional
    @Override
    public SimpleResponse updateSelectTheMainQuestionById(Long id, SelectTheMainIdeaQuestionUpdateRequest request) {
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
        if (request.getPassage() != null) {
            question.setPassage(request.getPassage());
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
                option.setIsCorrect(optionRequest.getIsCorrect() != null ?
                        optionRequest.getIsCorrect() : false);
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
                .message(String.format("Question with id : %s successfully updated !", id))
                .build();
    }
}
