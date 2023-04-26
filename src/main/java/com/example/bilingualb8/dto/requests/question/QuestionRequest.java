package com.example.bilingualb8.dto.requests.question;

import com.example.bilingualb8.dto.requests.file.FileRequest;
import com.example.bilingualb8.entity.File;
import com.example.bilingualb8.entity.Option;
import com.example.bilingualb8.enums.OptionType;
import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public record QuestionRequest(
        String title,
        String statement,
        @Enumerated(EnumType.STRING)
        QuestionType questionType,
        Integer duration,
        Integer minWords,
        Integer numberOfReplays,
        String correctAnswer,
        String passageQuestion,
        String passage,
        String audioText,
        Long testId,
        List<FileRequest> files,
        List<Option> options,
        int questionOrder,
        OptionType optionType
) {
}
