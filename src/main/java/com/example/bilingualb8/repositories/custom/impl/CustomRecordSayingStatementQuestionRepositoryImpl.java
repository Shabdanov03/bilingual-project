package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.questions.record_saying_statement.RecordSayingStatementQuestionResponse;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.repositories.custom.CustomRecordSayingStatementQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomRecordSayingStatementQuestionRepositoryImpl implements CustomRecordSayingStatementQuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<RecordSayingStatementQuestionResponse> getAllRecordSayingStatementQuestion() {
        String sql = """
                select q.id             as id,
                       q.title          as title,
                       q.statement      as statement,
                       q.question_type  as questionType,
                       q.duration       as duration,
                       q.min_words      as minWords,
                       q.question_order as questionOrder,
                       t.id             as testId
                from questions q join tests t on t.id = q.test_id
                where q.question_type = 'RECORD_SAYING_STATEMENT'
                """;

        return jdbcTemplate.query(sql, (resultSet, i) ->
                new RecordSayingStatementQuestionResponse(
                        resultSet.getLong("id"),
                        QuestionType.valueOf(resultSet.getString("questionType")),
                        resultSet.getString("title"),
                        resultSet.getString("statement"),
                        resultSet.getInt("duration"),
                        resultSet.getInt("minWords"),
                        resultSet.getInt("questionOrder"),
                        resultSet.getLong("testId")));
    }

    @Override
    public Optional<RecordSayingStatementQuestionResponse> getRecordSayingStatementQuestionById(Long id) {
        String sql = """
                select q.id             as id,
                       q.title          as title,
                       q.statement      as statement,
                       q.question_type  as questionType,
                       q.duration       as duration,
                       q.min_words      as minWords,
                       q.question_order as questionOrder,
                       t.id             as testId
                from questions q join tests t on t.id = q.test_id
                where q.question_type = 'RECORD_SAYING_STATEMENT' AND q.id = ?
                """;

        return jdbcTemplate.query(sql, (resultSet, i) ->
                new RecordSayingStatementQuestionResponse(
                        resultSet.getLong("id"),
                        QuestionType.valueOf(resultSet.getString("questionType")),
                        resultSet.getString("title"),
                        resultSet.getString("statement"),
                        resultSet.getInt("duration"),
                        resultSet.getInt("minWords"),
                        resultSet.getInt("questionOrder"),
                        resultSet.getLong("testId")), id).stream().findAny();
    }
}
