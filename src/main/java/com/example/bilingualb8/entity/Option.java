package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "options")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_id_gen")
    @SequenceGenerator(name = "option_id-gen", sequenceName = "option_id_seq", allocationSize = 1)
    private Long id;
    private String title;

    @Column(name = "is_correct")
    private Boolean isCorrect;
    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private Question question;
}
