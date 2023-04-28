package com.example.bilingualb8.services.impl.questionImpl;

import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;
import com.example.bilingualb8.repositories.custom.CustomRespondNWordsQuestionRepository;
import com.example.bilingualb8.services.questions.RespondNWordsQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondNWordsQuestionServiceImpl implements RespondNWordsQuestionService {
    private final CustomRespondNWordsQuestionRepository repository;
    @Override
    public SimpleResponse saveRespondNWordsQuestion(RespondNWordsQuestionRequest request) {
        return null;
    }

    @Override
    public List<RespondNWordsQuestionResponse> getAllRespondNWordsQuestion() {
        return repository.getAllRespondNWordsQuestion();
    }
}
