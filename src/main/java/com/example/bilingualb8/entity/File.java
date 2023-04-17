package com.example.bilingualb8.entity;

import com.example.bilingualb8.enums.FileType;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_id_gen")
    @SequenceGenerator(name = "file_id_gen", sequenceName = "file_id_gen", allocationSize = 1,initialValue = 5)
    private Long id;
    private String fileUrl;
    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    private Question question;
    @Enumerated(value = EnumType.STRING)
    private FileType fileType;
}
