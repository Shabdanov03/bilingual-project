package com.example.bilingualb8.repositories.custom.impl;

import com.example.bilingualb8.dto.responses.userResult.MyResultResponse;
import com.example.bilingualb8.enums.ResultStatus;
import com.example.bilingualb8.repositories.custom.CustomResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomResultRepositoryImpl implements CustomResultRepository {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<MyResultResponse> getAll(Long userId) {

        String sql = """
        SELECT r.id,
               r.date_of_submission,
               t.title as test_title,
               r.status,
               r.score
        FROM results r
        JOIN users u ON r.user_id = u.id
        JOIN tests t ON r.test_id = t.id
        WHERE u.id = ?;
        """;

        return jdbcTemplate.query(sql, (resultSet, i) ->
                        new MyResultResponse(
                                resultSet.getLong("id"),
                                resultSet.getObject("date_of_submission", LocalDate.class),
                                resultSet.getString("test_title"),
                                ResultStatus.valueOf(resultSet.getString("status")),
                                resultSet.getInt("score")
                        ),
                userId
        );
    }
}
