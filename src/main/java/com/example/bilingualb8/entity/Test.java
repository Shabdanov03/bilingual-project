package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_id_gen")
    @SequenceGenerator(name = "test_id-gen", sequenceName = "test_id_seq", allocationSize = 1)
    private Long id;
    private String title;

    @Column(name = "short_description")
    private String shortDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private List<Question> questions;
}
