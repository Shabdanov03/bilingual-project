package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.answer.UserAnswerResponse;
import com.example.bilingualb8.enums.AnswerStatus;
import com.example.bilingualb8.repositories.custom.CustomAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class CustomAnswerRepositoryImpl implements CustomAnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<UserAnswerResponse> getAnswerResponsesByResultId(Long resultId) {

        String answerQuery = """
                 SELECT
                    a.id as answer_id,
                    a.question_id as question_id,
                    a.answer_status as answer_status,
                    o.title as option_title,
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

        String answerQuery2 = """
                 SELECT
                    a.id as answer_id,
                    a.question_id as question_id,
                    a.answer_status as answer_status,
                    f.file_url as url,
                    a.number_of_plays as number_of_plays
                FROM answers a
                    JOIN answers_files af on a.id = af.answer_id
                    JOIN files f on f.id = af.files_id
                    JOIN questions q on a.question_id = q.id
                    JOIN results_answers ra on a.id = ra.answers_id
                    JOIN results r on ra.result_id = r.id
                    JOIN users u on u.id = r.user_id
                WHERE r.id = ?
                                """;

        String answerQuery3 = """
                 SELECT
                    a.id as answer_id,
                    a.question_id as question_id,
                    a.data as data,
                    a.answer_status as answer_status,
                    a.number_of_plays as number_of_plays
                FROM answers a
                    JOIN questions q on a.question_id = q.id
                    JOIN results_answers ra on a.id = ra.answers_id
                    JOIN results r on ra.result_id = r.id
                    JOIN users u on u.id = r.user_id
                WHERE r.id = ?
                                """;

        List<UserAnswerResponse> answerResponses1 = jdbcTemplate.query(answerQuery2, (resultSet, i) ->
                        new UserAnswerResponse(
                                resultSet.getLong("answer_id"),
                                resultSet.getLong("question_id"),
                                AnswerStatus.valueOf(resultSet.getString("answer_status")),
                                null,
                                null,
                                resultSet.getString("url"),
                                resultSet.getInt("number_of_plays")
                        ),
                resultId
        );

        List<UserAnswerResponse> answerResponses2 = jdbcTemplate.query(answerQuery, (resultSet, i) ->
                        new UserAnswerResponse(
                                resultSet.getLong("answer_id"),
                                resultSet.getLong("question_id"),
                                AnswerStatus.valueOf(resultSet.getString("answer_status")),
                                resultSet.getString("option_title"),
                                null,
                                null,
                                resultSet.getInt("number_of_plays")
                                ),
                resultId
        );

        List<UserAnswerResponse> answerResponses3 = jdbcTemplate.query(answerQuery3, (resultSet, i) ->
                        new UserAnswerResponse(
                                resultSet.getLong("answer_id"),
                                resultSet.getLong("question_id"),
                                AnswerStatus.valueOf(resultSet.getString("answer_status")),
                                null,
                                resultSet.getString("data"),
                                null,
                                resultSet.getInt("number_of_plays")
                        ),
                resultId
        );

        List<UserAnswerResponse> answerResponses = new ArrayList<>(answerResponses1);
        List<UserAnswerResponse> elementsToAdd = new ArrayList<>();

        if (answerResponses.isEmpty()) {
            answerResponses.addAll(answerResponses2);
        } else {
            for (UserAnswerResponse answerResponse : answerResponses) {
                boolean shouldAdd = true;
                for (UserAnswerResponse userAnswerResponse : answerResponses2) {
                    if (Objects.equals(answerResponse.getAnswerId(), userAnswerResponse.getAnswerId())
                            && Objects.equals(answerResponse.getQuestionId(), userAnswerResponse.getQuestionId())) {
                        shouldAdd = false;
                        break;
                    }
                    if (shouldAdd) {
                        elementsToAdd.add(userAnswerResponse);
                    }
                }
            }
        }

        answerResponses.addAll(elementsToAdd);
        elementsToAdd.clear();

        if (answerResponses.isEmpty()) {
            answerResponses.addAll(answerResponses3);
        } else {
            for (UserAnswerResponse answerResponse : answerResponses) {
                boolean shouldAdd = true;
                for (UserAnswerResponse userAnswerResponse : answerResponses3) {
                    if (Objects.equals(answerResponse.getAnswerId(), userAnswerResponse.getAnswerId())
                            && Objects.equals(answerResponse.getQuestionId(), userAnswerResponse.getQuestionId())) {
                        shouldAdd = false;
                        break;
                    }
                    if (shouldAdd) {
                        elementsToAdd.add(userAnswerResponse);
                    }
                }
            }
        }

        answerResponses.addAll(elementsToAdd);
        return answerResponses;
    }

    @Override
    public List<UserAnswerResponse> getAnswerResponsesByQuestionId(Long questionId, Long userId) {

        String answerQuery = """
                SELECT
                    a.id as answer_id,
                    a.question_id as question_id,
                    a.answer_status as answer_status,
                    o.title as option_title,
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

        String answerQuery2 = """
                SELECT
                a.id as answer_id,
                a.question_id as question_id,
                a.answer_status as answer_status,
                f.file_url as url,
                a.number_of_plays as number_of_plays
                FROM answers a
                JOIN answers_files af on a.id = af.answer_id
                JOIN files f on af.files_id = f.id
                JOIN questions q on a.question_id = q.id
                JOIN results_answers ra on a.id = ra.answers_id
                JOIN results r on ra.result_id = r.id
                JOIN users u on u.id = r.user_id
                WHERE q.id = ? AND a.user_id = ?""";


        String answerQuery3 = """
                SELECT
                a.id as answer_id,
                a.question_id as question_id,
                a.answer_status as answer_status,
                a.data as data,
                a.number_of_plays as number_of_plays
                FROM answers a
                JOIN questions q on a.question_id = q.id
                JOIN results_answers ra on a.id = ra.answers_id
                JOIN results r on ra.result_id = r.id
                JOIN users u on u.id = r.user_id
                WHERE q.id = ? AND a.user_id = ?""";

        List<UserAnswerResponse> answerResponses = jdbcTemplate.query(answerQuery2, (resultSet, i) ->
                        new UserAnswerResponse(
                                resultSet.getLong("answer_id"),
                                resultSet.getLong("question_id"),
                                AnswerStatus.valueOf(resultSet.getString("answer_status")),
                                null,
                                null,
                                resultSet.getString("url"),
                                resultSet.getInt("number_of_plays")
                        ),
                questionId, userId
        );

        List<UserAnswerResponse> answerResponses2 = jdbcTemplate.query(answerQuery, (resultSet, i) ->
                        new UserAnswerResponse(
                                resultSet.getLong("answer_id"),
                                resultSet.getLong("question_id"),
                                AnswerStatus.valueOf(resultSet.getString("answer_status")),
                                resultSet.getString("option_title"),
                                null,
                                null,
                                resultSet.getInt("number_of_plays")
                        ),
                questionId, userId
        );

        List<UserAnswerResponse> answerResponses3 = jdbcTemplate.query(answerQuery3, (resultSet, i) ->
                        new UserAnswerResponse(
                                resultSet.getLong("answer_id"),
                                resultSet.getLong("question_id"),
                                AnswerStatus.valueOf(resultSet.getString("answer_status")),
                                null,
                                resultSet.getString("data"),
                                null,
                                resultSet.getInt("number_of_plays")
                        ),
                questionId, userId
        );

        if (answerResponses.isEmpty()) answerResponses.addAll(answerResponses2);
        else {
            for (UserAnswerResponse answerRespons : answerResponses) {
                for (UserAnswerResponse userAnswerRespons : answerResponses2) {
                    if (!Objects.equals(answerRespons.getAnswerId(), userAnswerRespons.getAnswerId()) || !Objects.equals(answerRespons.getQuestionId(), userAnswerRespons.getQuestionId())) {
                        answerResponses.add(userAnswerRespons);
                    }
                }
            }
        }

        if (answerResponses.isEmpty()) answerResponses.addAll(answerResponses3);
        else {
            for (UserAnswerResponse answerRespons : answerResponses) {
                for (UserAnswerResponse userAnswerResponse : answerResponses3) {
                    if (!Objects.equals(answerRespons.getAnswerId(), userAnswerResponse.getAnswerId()) || !Objects.equals(answerRespons.getQuestionId(), userAnswerResponse.getQuestionId())) {
                        answerResponses.add(userAnswerResponse);
                    }
                }
            }
        }
        return answerResponses;
    }
}
