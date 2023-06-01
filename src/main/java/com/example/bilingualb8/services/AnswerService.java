package com.example.bilingualb8.services;

import com.example.bilingualb8.dto.requests.answer.AnswerRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Answer;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.User;

import java.util.List;

public interface AnswerService {
    List<Answer> createAnswers(List<AnswerRequest> answerRequests, User user);
    Answer createAnswer(AnswerRequest answerRequest, User user, Question question);
    SimpleResponse evaluating(Long answerId, float score);
}
