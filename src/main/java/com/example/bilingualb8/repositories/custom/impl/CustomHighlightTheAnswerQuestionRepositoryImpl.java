package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.questions.highlight_the_answer.HighlightTheAnswerQuestionResponse;
import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.repositories.custom.CustomHighlightTheAnswerQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomHighlightTheAnswerQuestionRepositoryImpl  implements CustomHighlightTheAnswerQuestionRepository {
   private final JdbcTemplate jdbcTemplate;
    @Override
    public List<HighlightTheAnswerQuestionResponse> getAllHighlightTheAnswerQuestion() {
        String sql = """
                SELECT   
                q.id as id,
                q.title as title,
                q.question_type as questionType,
                q.statement as statement,
                q.passage as passage,
                q.correct_answer as correct_answer,
                q.duration as duration,
                q.question_order as questionOrder,
                t.id as testId FROM questions q 
                join tests t on t.id = q.test_id
                WHERE q.question_type = 'HIGHLIGHT_THE_ANSWER'
                """;
        return jdbcTemplate.query(sql, (resultSet, i) ->
                new HighlightTheAnswerQuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        QuestionType.valueOf(resultSet.getString("questionType")),
                        resultSet.getString("statement"),
                        resultSet.getString("passage"),
                        resultSet.getString("correct_answer"),
                        resultSet.getInt("duration"),
                        resultSet.getInt("questionOrder"),
                        resultSet.getLong("testId")
                ));    }

    @Override
    public Optional<HighlightTheAnswerQuestionResponse> getHighlightTheAnswerQuestionById(Long id) {
        String sql = """
                SELECT   
                q.id as id,
                q.title as title,
                q.question_type as questionType,
                q.statement as statement,
                q.passage as passage,
                q.correct_answer as correct_answer,
                q.duration as duration,
                q.question_order as questionOrder,
                t.id as testId FROM questions q 
                join tests t on t.id = q.test_id
                WHERE q.question_type = 'HIGHLIGHT_THE_ANSWER' AND q.id = ?
                """;
        return jdbcTemplate.query(sql, (resultSet, i) ->
                new HighlightTheAnswerQuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        QuestionType.valueOf(resultSet.getString("questionType")),
                        resultSet.getString("statement"),
                        resultSet.getString("passage"),
                        resultSet.getString("correct_answer"),
                        resultSet.getInt("duration"),
                        resultSet.getInt("questionOrder"),
                        resultSet.getLong("testId")
                ),id).stream().findAny();    }
}
