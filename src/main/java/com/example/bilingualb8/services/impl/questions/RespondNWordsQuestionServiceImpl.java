package com.example.bilingualb8.services.impl.questions;

import com.example.bilingualb8.dto.requests.question.respondNWords.RespondNWordsQuestionRequest;
import com.example.bilingualb8.dto.requests.question.respondNWords.RespondNWordsQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.question.RespondNWordsQuestionResponse;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.RespondNWordsQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondNWordsQuestionServiceImpl implements RespondNWordsQuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    @Override
    public SimpleResponse respondNWordsSave(RespondNWordsQuestionRequest request) {
        Test test = testRepository.findById(request.testId()).orElseThrow(
                () -> new NotFoundException(String.format("Test with id : %s not found !", request.testId())));
        Question question = Question.builder()
                .title(request.title())
                .statement(request.statement())
                .duration(request.duration())
                .questionType(QuestionType.RESPOND_N_WORDS)
                .minWords(request.minWords())
                .test(test)
                .build();

        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with type : %s successfully saved ! ", request.questionType()))
                .build();
    }

    @Override
    public List<RespondNWordsQuestionResponse> getAllRespondNWords() {
        return questionRepository.getAllRespondNWords();
    }

    @Override
    public RespondNWordsQuestionResponse findByIdRespondNWords(Long id) {
        return questionRepository.findByIdRespondNWords(id).orElseThrow(
                () -> new NotFoundException(String.format("Question with id : %s not found !", id)));
    }

    @Override
    public SimpleResponse deleteByIdRespondNWords(Long id) {
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
    public SimpleResponse updateRespondNWords(Long id, RespondNWordsQuestionUpdateRequest request) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with id : %s not found !", id)));

        question.setTitle(request.title() != null ? request.title() : question.getTitle());
        question.setStatement(request.statement() != null ? request.statement() : question.getStatement());
        question.setDuration(request.duration() != null ? request.duration() : question.getDuration());
        question.setMinWords(request.minWords() != null ? request.minWords() : question.getMinWords());
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with id %s successfully updated !", id))
                .build();
    }
}
