package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "user_infos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userInfo_id_gen")
    @SequenceGenerator(name = "userInfo_id-gen", sequenceName = "userInfo_id_seq", allocationSize = 1)
    private Long id;
    private String email;
    private String password;
    private Role role;
    @OneToOne(cascade = {ALL})
    private User user;
}
