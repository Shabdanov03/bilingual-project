package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tests")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_id_gen")
    @SequenceGenerator(name = "test_id_gen", sequenceName = "test_id_gen", allocationSize = 1, initialValue = 2)
    private Long id;
    private String title;
    private String shortDescription;
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private List<Question> questions;
}
