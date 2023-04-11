package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tests")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_id_gen")
    @SequenceGenerator(name = "test_id-gen", sequenceName = "test_id_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String shortDescription;
    private Boolean isActive;
    private LocalDate createAt;
    private LocalDate modifiedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private List<Question> questions;
}
