package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.OptionType;
import com.example.bilingualb8.enums.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_gen")
    @SequenceGenerator(name = "question_id_gen", sequenceName = "question_id_gen", allocationSize = 1, initialValue = 11)
    private Long id;
    @Column(length = 100)
    private String title;
    @Column(length = 10000)
    private String statement;
    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;
    private Integer duration;
    private Integer minWords;
    private Integer numberOfReplays;
    @Column(length = 10000)
    private String correctAnswer;
    @Column(length = 10000)
    private String passage;
    private String audioText;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    @JsonIgnore
    private Test test;
    @OneToMany(mappedBy = "question", cascade = ALL)
    private List<File> files;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Option> options;
    private Integer questionOrder;
    @Enumerated(value = EnumType.STRING)
    private OptionType optionType;
}


