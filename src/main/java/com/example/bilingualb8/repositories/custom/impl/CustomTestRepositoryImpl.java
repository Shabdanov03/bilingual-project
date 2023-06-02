package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.file.FileResponse;
import com.example.bilingualb8.dto.responses.option.OptionResponse;
import com.example.bilingualb8.dto.responses.questions.QuestionResponse;
import com.example.bilingualb8.dto.responses.test.TestResponse;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.custom.CustomTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomTestRepositoryImpl implements CustomTestRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<TestResponse> getAll() {
        String testQuery = """
                select
                t.id as id,
                sum(q.duration) as duration,
                t.title as title,
                t.short_description as description,
                t.is_active as is_active
                from tests t
                left join questions q on t.id = q.test_id
                group by t.id
                """;

        List<TestResponse> tests = jdbcTemplate.query(testQuery, (resultSet, i) ->
                new TestResponse(
                        resultSet.getLong("id"),
                        resultSet.getInt("duration"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("is_active"),
                        null
                ));

        String questionQuery = """
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
                """;

        List<QuestionResponse> questions = jdbcTemplate.query(questionQuery, (resultSet, i) ->
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


        // TODO Inserting questions & files & options to related tests
        tests.forEach(test -> {
            List<QuestionResponse> questionsList = questions.stream()
                    .filter(question -> question.getTestId().equals(test.getId())).toList();

            questionsList.forEach(question -> {
                List<FileResponse> fileResponseList = files.stream()
                        .filter(file -> file.getQuestionId().equals(question.getId()))
                        .collect(Collectors.toList());
                List<OptionResponse> optionResponseList = options.stream()
                        .filter(option -> option.getQuestionId().equals(question.getId()))
                        .collect(Collectors.toList());
                question.setOptions(optionResponseList);
                question.setFiles(fileResponseList);
            });
            test.setQuestions(questionsList);
        });
        return tests;
    }

    @Override
    public Optional<TestResponse> getById(Long id) {
        String testQuery = """
                select
                t.id as id,
                sum(q.duration) as duration,
                t.title as title,
                t.short_description as description,
                t.is_active as is_active
                from tests t
                left join questions q on t.id = q.test_id
                WHERE t.id = ?
                group by t.id
                """;

        TestResponse test = jdbcTemplate.query(testQuery, (resultSet, i) ->
                new TestResponse(
                        resultSet.getLong("id"),
                        resultSet.getInt("duration"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("is_active"),
                        null
                ), id).stream().findAny().orElseThrow(
                () -> new NotFoundException(String.format("Test with id %s was not found", id)));

        String questionQuery = """
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
                WHERE t.id = ?
                """;

        List<QuestionResponse> questions = jdbcTemplate.query(questionQuery, (resultSet, i) ->
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
                ), id);

        String fileQuery = """
                SELECT
                f.id as fileId,
                f.file_type as fileType,
                f.file_url as fileUrl,
                q.id as questionId
                FROM files f JOIN questions q on f.question_id = q.id
                WHERE q.test_id = ?
                """;

        List<FileResponse> files = jdbcTemplate.query(fileQuery, (resultSet, i) ->
                new FileResponse(
                        resultSet.getLong("fileId"),
                        FileType.valueOf(resultSet.getString("fileType")),
                        resultSet.getString("fileUrl"),
                        resultSet.getLong("questionId")
                ), id);


        String optionQuery = """
                SELECT
                 o.id as id,
                 o.question_id as questionId,
                 o.file_url as fileUrl,
                 o.title as title,
                 o.option_order as option_order,
                 o.is_correct as isCorrect
                 FROM options o JOIN questions q on q.id = o.question_id
                 WHERE q.test_id = ?
                 """;


        List<OptionResponse> options = jdbcTemplate.query(optionQuery, (resultSet, i) ->
                new OptionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("isCorrect"),
                        resultSet.getLong("questionId"),
                        resultSet.getString("fileUrl"),
                        resultSet.getInt("option_order")
                ), id);


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

        test.setQuestions(questions);
        return Optional.of(test);
    }
}
