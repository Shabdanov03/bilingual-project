package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.FileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_id_gen")
    @SequenceGenerator(name = "file_id-gen", sequenceName = "file_id_seq", allocationSize = 1)
    private Long id;
    private String fileUrl;
    @ManyToOne(cascade = CascadeType.ALL)
    private Question questions;
    private FileType fileType;
}
