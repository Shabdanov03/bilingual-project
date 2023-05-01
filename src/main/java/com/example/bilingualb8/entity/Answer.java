package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "answers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_id_gen")
    @SequenceGenerator(name = "answer_id_gen", sequenceName = "answer_id_gen", allocationSize = 1,initialValue = 10)
    private Long id;
    @OneToOne(cascade = {REFRESH,MERGE,PERSIST,DETACH})
    private Question question;
    @ManyToOne(cascade = {REFRESH,MERGE,PERSIST,DETACH})
    private User user;
    @OneToMany(cascade = {REFRESH,MERGE,PERSIST,DETACH})
    private List<Option> options;
    private String data;
    private int evaluatedScore;
    private int numberOfWords;
    private int numberOfPlays;
    private boolean isChecked;
}
