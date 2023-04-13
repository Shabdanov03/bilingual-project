package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.ResultStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "submitted_answers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmittedAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_answer_id_gen")
    @SequenceGenerator(name = "question_answer_seq_gen", sequenceName = "question_answer_seq", allocationSize = 1)
    private Long id;
    @OneToMany
    private List<Question> questions;
    @OneToOne
    private Answer answer;
    @Column(name = "date_of_submission")
    private LocalDate dateOfSubmission;
    @ManyToOne
    private Test test;
    @Enumerated(value = EnumType.STRING)
    private ResultStatus status;
    private Integer score;
}
