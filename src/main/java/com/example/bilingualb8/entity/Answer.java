package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_id_gen")
    @SequenceGenerator(name = "answer_id-gen", sequenceName = "answer_id_seq", allocationSize = 1)
    private Long id;
    @OneToOne
    private Question question;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne
    private Option option;
    private String payLoad;
    private Integer evaluatedScore;
    private Boolean isCorrect;
}
