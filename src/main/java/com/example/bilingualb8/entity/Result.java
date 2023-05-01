package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.ResultStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_gen")
    @SequenceGenerator(name = "result_id_gen", sequenceName = "result_id_seq", allocationSize = 1, initialValue = 5)
    private Long id;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    private Test test;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    private User user;
    @Enumerated(value = EnumType.STRING)
    private ResultStatus status;
    private float score;
    private LocalDate dateOfSubmission;
    @OneToMany(cascade = ALL)
    private List<Answer> answers;
}
