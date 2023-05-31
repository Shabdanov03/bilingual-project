package com.example.bilingualb8.utils;

import com.example.bilingualb8.entity.Answer;
import com.example.bilingualb8.entity.Option;
import com.example.bilingualb8.repositories.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionScoreGenerator {
    private final OptionRepository optionRepository;

    public float scoreGenerator(Answer answer) {
        List<Option> options = optionRepository.findByQuestionId(answer.getQuestion().getId());

        int trueOptions = 0;

        for (Option option : options) {
            if (option.getIsCorrect()){
                trueOptions++;
            }
        }

        int correctAnswers = 0;

        for (Option option : answer.getOptions()) {
            if (option.getIsCorrect()){
                correctAnswers++;
            }
        }

        if (options.size() == answer.getOptions().size() || answer.getOptions().size() == 0 || correctAnswers == 0){
            return 0; // if user chosen all options, answer will 0 or correct answer 0
        } else if (answer.getOptions().size() == options.size() - 1){ // if user chosen all-1 options
            if (correctAnswers == trueOptions){
                return 2; // and true options equal to correct answer
            } else {
                return 0; // and true options not equal to correct answer
            }
        } else {
            return (float) correctAnswers * 10 / trueOptions;
        }
    }
}
