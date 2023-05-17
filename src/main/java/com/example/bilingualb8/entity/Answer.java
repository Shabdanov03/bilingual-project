package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.AnswerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "answers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_id_gen")
    @SequenceGenerator(name = "answer_id_gen", sequenceName = "answer_id_gen", allocationSize = 1, initialValue = 11)
    private Long id;
    @OneToOne(cascade = {REFRESH, MERGE, PERSIST, DETACH})
    private Question question;
    @ManyToOne(cascade = {REFRESH, MERGE, PERSIST, DETACH})
    private User user;
    @OneToMany(cascade = {REFRESH, MERGE, PERSIST, DETACH})
    private List<Option> options;
    private String data;
    private float evaluatedScore;
    private int numberOfWords;
    private int numberOfPlays;
    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;
    @OneToMany(cascade = ALL)
    private List<File> files;
}
