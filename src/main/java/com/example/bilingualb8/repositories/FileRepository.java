package com.example.bilingualb8.repositories;

import com.example.bilingualb8.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
