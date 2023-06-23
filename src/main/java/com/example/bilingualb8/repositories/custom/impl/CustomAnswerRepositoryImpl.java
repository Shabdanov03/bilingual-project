package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.answer.UserAnswerResponse;
import com.example.bilingualb8.enums.AnswerStatus;
import com.example.bilingualb8.repositories.custom.CustomAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomAnswerRepositoryImpl implements CustomAnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<UserAnswerResponse> getAnswerResponsesByResultId(Long resultId) {// add option order
        String answerQuery = """
                 SELECT
                    a.id as answer_id,
                    a.question_id as question_id,
                    a.answer_status as answer_status,
                    o.title as option_title,
                    a.data as data,
                    a.number_of_plays as number_of_plays
                FROM answers a
                    JOIN answers_options ao on a.id = ao.answer_id
                    JOIN options o on ao.options_id = o.id
                    JOIN questions q on a.question_id = q.id
                    JOIN results_answers ra on a.id = ra.answers_id
                    JOIN results r on ra.result_id = r.id
                    JOIN users u on u.id = r.user_id
                WHERE r.id = ?
                                """;

        return jdbcTemplate.query(answerQuery, (resultSet, i) ->
                        new UserAnswerResponse(
                                resultSet.getLong("answer_id"),
                                resultSet.getLong("question_id"),
                                AnswerStatus.valueOf(resultSet.getString("answer_status")),
                                resultSet.getString("option_title"),
                                resultSet.getString("data"),
                                resultSet.getInt("number_of_plays")
                        ),
                resultId
        );
    }

    @Override
    public List<UserAnswerResponse> getAnswerResponsesByQuestionId(Long questionId, Long userId) {
        String answerQuery = """
                SELECT
                    a.id as answer_id,
                    a.question_id as question_id,
                    a.answer_status as answer_status,
                    o.title as option_title,
                    a.data as data,
                    a.number_of_plays as number_of_plays
                FROM answers a
                    JOIN answers_options ao on a.id = ao.answer_id
                    JOIN options o on ao.options_id = o.id
                    JOIN questions q on a.question_id = q.id
                    JOIN results_answers ra on a.id = ra.answers_id
                    JOIN results r on ra.result_id = r.id
                    JOIN users u on u.id = r.user_id
                WHERE q.id = ? AND a.user_id = ?
                                                """;

        return jdbcTemplate.query(answerQuery, (resultSet, i) ->
                        new UserAnswerResponse(
                                resultSet.getLong("answer_id"),
                                resultSet.getLong("question_id"),
                                AnswerStatus.valueOf(resultSet.getString("answer_status")),
                                resultSet.getString("option_title"),
                                resultSet.getString("data"),
                                resultSet.getInt("number_of_plays")
                        ),
                questionId, userId
        );
    }

}
