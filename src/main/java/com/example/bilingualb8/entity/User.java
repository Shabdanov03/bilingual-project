package com.example.bilingualb8.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_gen", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @PrePersist
    protected void onCreate() {
        modifiedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        modifiedAt = LocalDateTime.now();
    }
}
