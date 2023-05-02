package com.example.bilingualb8.repositories;

import com.example.bilingualb8.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionsByTestId(Long testId);
}