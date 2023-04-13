package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_gen")
    @SequenceGenerator(name = "question_id-gen", sequenceName = "question_id_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String statement;

    @Column(name = "question_type")
    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;
    private LocalDate duration;
    @Column(name = "min_words")
    private Integer minWords;
    @Column(name = "number_of_replays")
    private Integer numberOfReplays;
    @Column(name = "correct_answer")
    private String correctAnswer;
    @Column(name = "passage_question")
    private String passageQuestion;
    private String passage;

    @Column(name = "audio_text")
    private String audioText;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    private Test test;
    @OneToMany(mappedBy = "question", cascade = ALL)
    private List<File> files;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Option> options;
}
