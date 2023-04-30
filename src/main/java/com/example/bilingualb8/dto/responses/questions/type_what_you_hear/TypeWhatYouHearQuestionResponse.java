package com.example.bilingualb8.dto.responses.questions.type_what_you_hear;

import com.example.bilingualb8.dto.responses.file.FileResponse;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TypeWhatYouHearQuestionResponse{
   private Long id;
   private String title;
   private Integer duration;
   @Builder.Default
   @Enumerated(EnumType.STRING)
   private QuestionType questionType = QuestionType.TYPE_WHAT_YOU_HEAR;
   private FileResponse file;
   private String correctAnswer;
   private Long testId;

   public TypeWhatYouHearQuestionResponse(Long id, String title, Integer duration, QuestionType questionType,
                                          // todo file response stuff
                                          Long questionId, Long fileId, FileType fileType,String fileUrl,
                                          // todo ....
                                          String correctAnswer, Long testId) {
      this.id = id;
      this.title = title;
      this.duration = duration;
      this.questionType = questionType;
      this.file = new FileResponse(fileId, fileType, fileUrl, questionId);
      this.correctAnswer = correctAnswer;
      this.testId = testId;
   }
}
