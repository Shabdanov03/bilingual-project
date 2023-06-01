package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.entity.File;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Override
    public File createAudioFile(String fileUrl, Question question) {
        File file = new File();
        file.setFileType(FileType.AUDIO);
        file.setFileUrl(fileUrl);
        file.setQuestion(question);
        return file;
    }
}
