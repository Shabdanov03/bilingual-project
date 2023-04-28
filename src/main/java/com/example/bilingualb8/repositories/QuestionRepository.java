package com.example.bilingualb8.repositories;

<<<<<<< HEAD
import com.example.bilingualb8.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
=======
import com.example.bilingualb8.dto.responses.question.RespondNWordsQuestionResponse;
import com.example.bilingualb8.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select new com.example.bilingualb8.dto.responses.question.RespondNWordsQuestionResponse" +
            "(q.id,q.title,q.statement,q.duration,q.questionType,q.minWords,t.id) from Question q join q.test t where q.questionType = 'RESPOND_N_WORDS'")
    List<RespondNWordsQuestionResponse> getAllRespondNWords();
    @Query("select new com.example.bilingualb8.dto.responses.question.RespondNWordsQuestionResponse" +
            "(q.id,q.title,q.statement,q.duration,q.questionType,q.minWords,t.id) from Question q join q.test t where q.questionType = 'RESPOND_N_WORDS' and q.id = ?1")
    Optional<RespondNWordsQuestionResponse> findByIdRespondNWords(Long id);
>>>>>>> 347b759a835a654e3275416dc4d5558b652a66d8
}
