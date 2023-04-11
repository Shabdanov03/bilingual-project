package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_gen")
    @SequenceGenerator(name = "question_id-gen", sequenceName = "question_id_seq", allocationSize = 1)
    private Long id;
    private String title;

    private String statement;
    private String questionStatement;
    private QuestionType questionType;
   private LocalDate duration;
   private Integer min_words;
   private Integer numberOfReplays;
   private String correct_answer;
   private String questionToThePassage;
   private String  passage;
   private String audioText;
    @ManyToOne
    private Test test;
   @OneToMany(mappedBy = "questions")
   private List<File> files;
   @OneToOne(cascade = CascadeType.ALL)
   private Option correct_option;


}
