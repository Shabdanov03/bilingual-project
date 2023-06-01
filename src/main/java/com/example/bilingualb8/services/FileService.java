package com.example.bilingualb8.services;

import com.example.bilingualb8.entity.File;
import com.example.bilingualb8.entity.Question;

public interface FileService {
    File createAudioFile(String fileUrl, Question question);
}
