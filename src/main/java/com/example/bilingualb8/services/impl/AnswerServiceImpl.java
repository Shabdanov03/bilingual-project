package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.dto.requests.answer.AnswerRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.*;
import com.example.bilingualb8.enums.AnswerStatus;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.enums.ResultStatus;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.AnswerRepository;
import com.example.bilingualb8.repositories.OptionRepository;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.ResultRepository;
import com.example.bilingualb8.repositories.custom.CustomResultRepository;
import com.example.bilingualb8.services.AnswerService;
import com.example.bilingualb8.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final FileService fileService;
    private final AnswerRepository answerRepository;
    private final CustomResultRepository customResultRepository;
    private final ResultRepository resultRepository;

    @Override
    public List<Answer> createAnswers(List<AnswerRequest> answerRequests, User user) {
        List<Answer> answers = new ArrayList<>();

        for (AnswerRequest answerRequest : answerRequests) {
            Question question = questionRepository.findById(answerRequest.getQuestionId()).orElseThrow(() ->
                    new NotFoundException(String.format("Question with id : %s doesn't exist !", answerRequest.getQuestionId())));
            Answer answer = createAnswer(answerRequest, user, question);
            answers.add(answer);
        }
        return answers;
    }

    @Override
    public Answer createAnswer(AnswerRequest answerRequest, User user, Question question) {
        Answer answer = new Answer();
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setAnswerStatus(AnswerStatus.NOT_EVALUATED);

        if (answerRequest.getOptionsIds() != null) {
            List<Option> options = optionRepository.findAllById(answerRequest.getOptionsIds());
            answer.setOptions(options);
        }

        if (answerRequest.getNumberOfPlays() != null) {
            answer.setNumberOfPlays(answerRequest.getNumberOfPlays());
        }

        if (answerRequest.getFileUrl() != null && question.getQuestionType().equals(QuestionType.RECORD_SAYING_STATEMENT)) {
            File file = fileService.createAudioFile(answerRequest.getFileUrl(), question);
            answer.setFiles(Collections.singletonList(file));
        }

        if (answerRequest.getData() != null) {
            if (isDataApplicableForAnswer(answerRequest.getData(), question)) {
                answer.setData(answerRequest.getData());
                answer.setNumberOfWords(answerRequest.getData().split(" ").length);
            }
        }
        return answer;
    }

    @Override
    public SimpleResponse evaluating(Long answerId, float score) {

        Answer answer = answerRepository.findById(answerId).orElseThrow(() ->
                new NotFoundException(String.format("Answer with id : %s doesn't exist !", answerId)));

        answer.setEvaluatedScore(score);
        answer.setAnswerStatus(AnswerStatus.EVALUATED);

        Long resultIdByAnswerId = customResultRepository.getResultIdByAnswerId(answerId);

        Result result = resultRepository.findById(resultIdByAnswerId).orElseThrow(()->
                new NotFoundException(String.format("Answer with id : %s doesn't exist !", answerId)));

        boolean allTrue = result.getAnswers().stream()
                .allMatch(a -> a.getAnswerStatus() == AnswerStatus.EVALUATED);

        if (allTrue){
            result.setStatus(ResultStatus.EVALUATED);
        }else result.setStatus(ResultStatus.NOT_EVALUATED);
        result.setScore(result.getScore() + score);

        resultRepository.save(result);
        answerRepository.save(answer);

        return SimpleResponse.builder().message(String.format("Answer with : %d id successfully scored", answerId)).build();
    }

    private boolean isDataApplicableForAnswer(String data, Question question) {
        return question.getQuestionType() == QuestionType.DESCRIBE_IMAGE
                || question.getQuestionType() == QuestionType.HIGHLIGHT_THE_ANSWER
                || question.getQuestionType() == QuestionType.RESPOND_N_WORDS
                || question.getQuestionType() == QuestionType.TYPE_WHAT_YOU_HEAR;
    }
}
