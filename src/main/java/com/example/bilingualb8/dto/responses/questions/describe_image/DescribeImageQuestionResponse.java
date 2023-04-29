package com.example.bilingualb8.dto.responses.questions.describe_image;


import com.example.bilingualb8.dto.responses.file.FileResponse;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class DescribeImageQuestionResponse {
    private Long id;
    private String title;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.DESCRIBE_IMAGE;
    private Integer duration;
    private Integer questionOrder;
    private FileResponse file;
    private Long testId;

    public DescribeImageQuestionResponse(Long id, String title, QuestionType questionType, Integer duration, Integer questionOrder, Long fileId, FileType fileType, String fileUrl, Long questionId, Long testId) {
        this.id = id;
        this.title = title;
        this.questionType = questionType;
        this.duration = duration;
        this.questionOrder = questionOrder;
        this.file = new FileResponse(fileId, fileType, fileUrl, questionId);
        this.testId = testId;
    }
}
