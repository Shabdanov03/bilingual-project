package com.example.bilingualb8.dto.responses.questions.respond_n_words;

import com.example.bilingualb8.enums.QuestionType;
 import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RespondNWordsQuestionResponse {
    private Long id;
    private String title;
    private String statement;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.RESPOND_N_WORDS;
    private Integer duration;
    private Integer questionOrder;
    private Integer minWords;
    private Long testId;
}
