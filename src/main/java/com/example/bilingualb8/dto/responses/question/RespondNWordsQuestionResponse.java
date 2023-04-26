package com.example.bilingualb8.dto.responses.question;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

@Builder
public record RespondNWordsQuestionResponse(
        Long id,
        String title,
        String statement,
        Integer duration,
        @Enumerated(EnumType.STRING)
        QuestionType questionType,
        Integer minWords,
        Long testId
) {
        public RespondNWordsQuestionResponse(Long id, String title, String statement, Integer duration,
                                             QuestionType questionType, Integer minWords, Long testId) {
                this.id = id;
                this.title = title;
                this.statement = statement;
                this.duration = duration;
                this.questionType = questionType;
                this.minWords = minWords;
                this.testId = testId;
        }
}
