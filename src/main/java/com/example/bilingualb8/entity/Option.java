package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "options")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_id_gen")
    @SequenceGenerator(name = "option_id-gen", sequenceName = "option_id_seq", allocationSize = 1)
    private Long id;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    private Question question;
}
