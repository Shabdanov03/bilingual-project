package com.example.bilingualb8.repositories;

import com.example.bilingualb8.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    Answer findAnswerByQuestionId(Long id);
}
