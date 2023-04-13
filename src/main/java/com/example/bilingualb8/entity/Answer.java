package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "answers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_id_gen")
    @SequenceGenerator(name = "answer_id-gen", sequenceName = "answer_id_seq", allocationSize = 1)
    private Long id;
    @OneToOne
    private Question question;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToMany
    private List<Option> options;
    private String payload;

    @Column(name = "evaluated_score")
    private Integer evaluatedScore;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
