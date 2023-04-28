package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomRespondNWordsQuestionRepository {
    private final JdbcTemplate jdbcTemplate;
   public List<RespondNWordsQuestionResponse> getAllRespondNWordsQuestion(){
        String sql = """
                SELECT q.id as id, q.title as title,q.statement as statement,
                q.question_type as questionType,q.duration as duration,q.min_words as minWords,
                t.id as testId FROM questions q join tests t on t.id = q.test_id
                 WHERE q.question_type = 'RESPOND_N_WORDS'
                """;
        return jdbcTemplate.query(sql,(resultSet,i)->
               new RespondNWordsQuestionResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("statement"),
                        resultSet.getString("questionType"),
                        resultSet.getInt("duration"),
                        resultSet.getInt("minWords"),
                        resultSet.getLong("testId")
                ));
    }
public interface CustomRespondNWordsQuestionRepository {
}
