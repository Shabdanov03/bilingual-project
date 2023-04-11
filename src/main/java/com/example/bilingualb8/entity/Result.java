package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.ResultStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_gen")
    @SequenceGenerator(name = "result_id-gen", sequenceName = "result_id_seq", allocationSize = 1)
    private Long id;
    @OneToOne
    private Test test;
    @ManyToOne
    private User user;
    private ResultStatus status;
    private Integer score;
    private LocalDate dateOfSubmission;


}
