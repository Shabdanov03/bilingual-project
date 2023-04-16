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
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_gen", allocationSize = 1,initialValue = 5)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private UserInfo userInfo;
    private Boolean isActive;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
}
