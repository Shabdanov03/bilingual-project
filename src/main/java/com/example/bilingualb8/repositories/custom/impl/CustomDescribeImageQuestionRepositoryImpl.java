package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.questions.describe_image.DescribeImageQuestionResponse;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.repositories.custom.CustomDescribeImageQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomDescribeImageQuestionRepositoryImpl implements CustomDescribeImageQuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<DescribeImageQuestionResponse> getAllDescribeImageQuestion() {
        String sql = """
                select
                    q.id as id,
                    q.title as title,
                    q.question_type as question_type,
                    q.duration as duration,
                    q.question_order as question_order,
                    f.id as file_id,
                    f.file_type as file_type,
                    f.file_url as file_url,
                    f.question_id as question_id,
                    t.id as test_id
                from questions q
                    join files f on q.id = f.question_id
                    join tests t on t.id = q.test_id
                where q.question_type = 'DESCRIBE_IMAGE'""";

        return jdbcTemplate.query(sql, (resultSet, i) ->
                new DescribeImageQuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        QuestionType.valueOf(resultSet.getString("question_type")),
                        resultSet.getInt("duration"),
                        resultSet.getInt("question_order"),
                        resultSet.getLong("file_id"),
                        FileType.valueOf(resultSet.getString("file_type")),
                        resultSet.getString("file_url"),
                        resultSet.getLong("question_id"),
                        resultSet.getLong("test_id")
                ));
    }

    @Override
    public Optional<DescribeImageQuestionResponse> getDescribeImageQuestionById(Long id) {
        String sql = """
                select
                    q.id as id,
                    q.title as title,
                    q.question_type as question_type,
                    q.duration as duration,
                    q.question_order as question_order,
                    f.id as file_id,
                    f.file_type as file_type,
                    f.file_url as file_url,
                    f.question_id as question_id,
                    t.id as test_id
                from questions q
                    join files f on q.id = f.question_id
                    join tests t on t.id = q.test_id
                where q.question_type = 'DESCRIBE_IMAGE' AND q.id = ?""";
        return jdbcTemplate.query(sql, (resultSet, i) ->
                new DescribeImageQuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        QuestionType.valueOf(resultSet.getString("question_type")),
                        resultSet.getInt("duration"),
                        resultSet.getInt("question_order"),
                        resultSet.getLong("file_id"),
                        FileType.valueOf(resultSet.getString("file_type")),
                        resultSet.getString("file_url"),
                        resultSet.getLong("question_id"),
                        resultSet.getLong("test_id")
                ), id).stream().findAny();
    }
}
