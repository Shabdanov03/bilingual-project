package com.example.bilingualb8.repositories;

import com.example.bilingualb8.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}