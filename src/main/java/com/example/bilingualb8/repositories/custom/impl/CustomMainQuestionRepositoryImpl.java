package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.file.FileResponse;
import com.example.bilingualb8.dto.responses.option.OptionResponse;
import com.example.bilingualb8.dto.responses.questions.MainQuestionMiniResponse;
import com.example.bilingualb8.dto.responses.questions.MainQuestionResponse;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.custom.CustomMainQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomMainQuestionRepositoryImpl implements CustomMainQuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<MainQuestionMiniResponse> getAllQuestions() {
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
                q.audio_text as audio_text,
                t.id as test_id
                FROM questions q join tests t on t.id = q.test_id
                """;
        return jdbcTemplate.query(sql, (resultSet, i) ->
                new MainQuestionMiniResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("statement"),
                        QuestionType.valueOf(resultSet.getString("question_type")),
                        resultSet.getInt("duration"),
                        resultSet.getInt("min_words"),
                        resultSet.getInt("number_of_replays"),
                        resultSet.getString("correct_answer"),
                        resultSet.getString("passage"),
                        resultSet.getString("audio_text"),
                        resultSet.getLong("test_id")
                ));
    }

    @Override
    public Optional<MainQuestionResponse> getQuestionById(Long id) {


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
                ),id);

        String optionsQuery = """
                SELECT 
                o.id as id,
                o.question_id as questionId,
                o.file_url as fileUrl,
                o.title as title,
                o.is_correct as isCorrect
                FROM options o WHERE o.question_id = ?
                """;

        List<OptionResponse> options = jdbcTemplate.query(optionsQuery, (resultSet, i) ->
                new OptionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("isCorrect"),
                        resultSet.getLong("questionId"),
                        resultSet.getString("fileUrl")
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
                q.audio_text as audio_text,
                t.id as test_id
                FROM questions q join tests t on t.id = q.test_id
                where q.id = ?
                """;

        MainQuestionResponse response = jdbcTemplate.query(sql, (resultset, i) ->
                new MainQuestionResponse(
                        resultset.getLong("id"),
                        resultset.getString("title"),
                        resultset.getString("statement"),
                        QuestionType.valueOf(resultset.getString("question_type")),
                        resultset.getInt("duration"),
                        resultset.getInt("min_words"),
                        resultset.getInt("number_of_replays"),
                        resultset.getString("correct_answer"),
                        resultset.getString("passage"),
                        resultset.getString("audio_text"),
                        resultset.getLong("test_id"),
                        null,
                        null
                ), id).stream().findAny().orElseThrow(() -> new NotFoundException(String.format("Question with id %s was not found", id)));

        response.setFiles(fileResponses);
        response.setOptions(options);

        return Optional.of(response);
    }
}
