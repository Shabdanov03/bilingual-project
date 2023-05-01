package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.responses.questions.MainQuestionMiniResponse;
import com.example.bilingualb8.dto.responses.questions.MainQuestionResponse;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.custom.CustomMainQuestionRepository;
import com.example.bilingualb8.services.questions.MainQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MainQuestionServiceImpl implements MainQuestionService {
    private final CustomMainQuestionRepository customQuestionRepository;
    @Override
    public List<MainQuestionMiniResponse> getAllQuestions() {
        return customQuestionRepository.getAllQuestions();
    }

    @Override
    public MainQuestionResponse getQuestionById(Long id) {
        return customQuestionRepository.getQuestionById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Question with id : %s doesn't exist !",id)));
    }
}
