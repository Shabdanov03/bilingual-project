package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.List;




@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id-gen", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;
    private Boolean isActive;
    private LocalDate createAt;
    private LocalDate modifiedAt;
    @OneToOne(cascade = CascadeType.ALL)
    private Answer answer;


}
