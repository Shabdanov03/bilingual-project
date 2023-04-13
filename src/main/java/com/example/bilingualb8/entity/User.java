package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;




@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id-gen", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;
}
