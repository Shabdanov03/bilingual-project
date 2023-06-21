package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.answer.UserAnswerResponse;
import com.example.bilingualb8.dto.responses.file.FileResponse;
import com.example.bilingualb8.dto.responses.option.OptionResponse;
import com.example.bilingualb8.dto.responses.questions.EvaluateQuestionResponse;
import com.example.bilingualb8.dto.responses.questions.QuestionResponse;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.custom.CustomAnswerRepository;
import com.example.bilingualb8.repositories.custom.CustomQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomQuestionRepositoryImpl implements CustomQuestionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CustomAnswerRepository customAnswerRepository;

    @Override
    public List<QuestionResponse> getAllQuestions() {
        String sql = """
                SELECT
                q.id as id,
                q.title as title,
                q.statement as statement,
                q.question_type as question_type,
                q.duration as duration,
                q.min_words as min_words,
                q.number_of_replays as number_of_replays,
                q.correct_answer as correct_answer,
                q.passage as passage,
                q.question_order as question_order,
                q.is_active as is_active,
                q.audio_text as audio_text,
                t.id as test_id
                FROM questions q join tests t on t.id = q.test_id
                """;

        String fileQuery = """
                SELECT
                f.id as fileId,
                f.file_type as fileType,
                f.file_url as fileUrl,
                f.question_id as questionId
                FROM files f
                """;

        List<FileResponse> files = jdbcTemplate.query(fileQuery, (resultSet, i) ->
                new FileResponse(
                        resultSet.getLong("fileId"),
                        FileType.valueOf(resultSet.getString("fileType")),
                        resultSet.getString("fileUrl"),
                        resultSet.getLong("questionId")
                ));

        List<QuestionResponse> questions = jdbcTemplate.query(sql, (resultSet, i) ->
                new QuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("statement"),
                        QuestionType.valueOf(resultSet.getString("question_type")),
                        resultSet.getInt("duration"),
                        resultSet.getInt("min_words"),
                        resultSet.getInt("number_of_replays"),
                        resultSet.getString("correct_answer"),
                        resultSet.getString("passage"),
                        resultSet.getInt("question_order"),
                        resultSet.getString("audio_text"),
                        resultSet.getLong("test_id"),
                        null,
                        null,
                        resultSet.getBoolean("is_active")
                ));


        String optionQuery = """
                SELECT
                 o.id as id,
                 o.question_id as questionId,
                 o.file_url as fileUrl,
                 o.title as title,
                 o.option_order as option_order,
                 o.is_correct as isCorrect
                 FROM options o
                 """;


        List<OptionResponse> options = jdbcTemplate.query(optionQuery, (resultSet, i) ->

                new OptionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("isCorrect"),
                        resultSet.getLong("questionId"),
                        resultSet.getString("fileUrl"),
                        resultSet.getInt("option_order")
                ));

        // TODO Inserting files & options to related questions
        questions.forEach(question -> {
            List<FileResponse> fileResponseList = files.stream()
                    .filter(file -> file.getQuestionId().equals(question.getId()))
                    .collect(Collectors.toList());
            List<OptionResponse> optionResponseList = options.stream()
                    .filter(option -> option.getQuestionId().equals(question.getId()))
                    .collect(Collectors.toList());
            question.setOptions(optionResponseList);
            question.setFiles(fileResponseList);
        });

        return questions;
    }

    @Override
    public Optional<QuestionResponse> getQuestionById(Long id) {


        String fileSql = """
                SELECT
                f.id as file_id,
                f.file_type as file_type,
                f.file_url as file_url,
                f.question_id as question_id
                FROM files f WHERE f.question_id = ?
                """;

        List<FileResponse> fileResponses = jdbcTemplate.query(fileSql, (resultSet, i) ->
                new FileResponse(
                        resultSet.getLong("file_id"),
                        FileType.valueOf(resultSet.getString("file_type")),
                        resultSet.getString("file_url"),
                        resultSet.getLong("question_id")
                ), id);

        String optionsQuery = """
                SELECT
                o.id as id,
                o.question_id as questionId,
                o.file_url as fileUrl,
                o.title as title,
                o.option_order as option_order,
                o.is_correct as isCorrect
                FROM options o WHERE o.question_id = ?
                """;

        List<OptionResponse> options = jdbcTemplate.query(optionsQuery, (resultSet, i) ->
                new OptionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("isCorrect"),
                        resultSet.getLong("questionId"),
                        resultSet.getString("fileUrl"),
                        resultSet.getInt("option_order")
                ), id);

        String sql = """
                SELECT
                q.id as id,
                q.title as title,
                q.statement as statement,
                q.question_type as question_type,
                q.duration as duration,
                q.min_words as min_words,
                q.number_of_replays as number_of_replays,
                q.correct_answer as correct_answer,
                q.passage as passage,
                q.question_order as question_order,
                q.audio_text as audio_text,
                q.is_active as is_active,
                t.id as test_id
                FROM questions q join tests t on t.id = q.test_id
                where q.id = ?
                """;

        QuestionResponse response = jdbcTemplate.query(sql, (resultset, i) ->
                new QuestionResponse(
                        resultset.getLong("id"),
                        resultset.getString("title"),
                        resultset.getString("statement"),
                        QuestionType.valueOf(resultset.getString("question_type")),
                        resultset.getInt("duration"),
                        resultset.getInt("min_words"),
                        resultset.getInt("number_of_replays"),
                        resultset.getString("correct_answer"),
                        resultset.getString("passage"),
                        resultset.getInt("question_order"),
                        resultset.getString("audio_text"),
                        resultset.getLong("test_id"),
                        null,
                        null,
                        resultset.getBoolean("is_active")
                ), id).stream().findAny().orElseThrow(() -> new NotFoundException(String.format("Question with id %s was not found", id)));

        response.setFiles(fileResponses);
        response.setOptions(options);
        return Optional.of(response);
    }

    @Override
    public EvaluateQuestionResponse getEvaluateQuestionByIdes(Long answerId, Long questionId) {

        String sql = """
                SELECT concat(u.first_name, ' ', u.last_name) as full_name,
                t.title as title,
                a.evaluated_score as score,
                a.id as answer_id
                FROM answers a
                JOIN users u on a.user_id = u.id
                JOIN questions q on a.question_id = q.id
                JOIN tests t on q.test_id = t.id
                WHERE a.id = ?
                """;

        Optional<QuestionResponse> questionById = getQuestionById(questionId);
        List<UserAnswerResponse> answerResponses = customAnswerRepository.getAnswerResponsesByQuestionId(questionId);

        EvaluateQuestionResponse evaluateQuestionResponses = jdbcTemplate.query(sql, (resultSet, i) ->
                        new EvaluateQuestionResponse(
                                resultSet.getString("full_name"),
                                resultSet.getString("title"),
                                resultSet.getFloat("score"),
                                resultSet.getLong("answer_id")
                        ),
                answerId
        ).stream().findFirst().orElseThrow(()-> new NotFoundException("first index is empty"));

        evaluateQuestionResponses.setQuestionResponse(questionById.orElseThrow(()-> new NotFoundException(String.format("question with id : %s not found", questionId))));
        evaluateQuestionResponses.setUserAnswerResponse(answerResponses);
        return evaluateQuestionResponses;
    }
}
