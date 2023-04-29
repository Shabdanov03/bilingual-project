package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.repositories.custom.CustomRespondNWordsQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CustomRespondNWordsQuestionRepositoryImpl implements CustomRespondNWordsQuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<RespondNWordsQuestionResponse> getAllRespondNWordsQuestion() {
        String sql = """
                SELECT q.id as id, q.title as title,q.statement as statement,
                q.question_type as questionType,q.duration as duration,q.question_order as questionOrder,q.min_words as minWords,
                t.id as testId FROM questions q join tests t on t.id = q.test_id
                 WHERE q.question_type = 'RESPOND_N_WORDS'
                """;
        return jdbcTemplate.query(sql, (resultSet, i) ->
                new RespondNWordsQuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("statement"),
                        QuestionType.valueOf(resultSet.getString("questionType")),                        resultSet.getInt("duration"),
                        resultSet.getInt("questionOrder"),
                        resultSet.getInt("minWords"),
                        resultSet.getLong("testId")
                ));
    }

    @Override
    public Optional<RespondNWordsQuestionResponse> getRespondNWordsQuestionById(Long id) {
        String sql = """
                SELECT q.id as id, q.title as title,q.statement as statement,
                q.question_type as questionType,q.duration as duration,q.question_order as questionOrder,q.min_words as minWords,
                t.id as testId FROM questions q join tests t on t.id = q.test_id
                 WHERE q.question_type = 'RESPOND_N_WORDS' and q.id = ?
                """;
        return jdbcTemplate.query(sql, (resultSet, i) ->
                new RespondNWordsQuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("statement"),
                        QuestionType.valueOf(resultSet.getString("questionType")),
                        resultSet.getInt("duration"),
                        resultSet.getInt("minWords"),
                        resultSet.getInt("questionOrder"),
                        resultSet.getLong("testId")
                ), id).stream().findAny();
    }
}
