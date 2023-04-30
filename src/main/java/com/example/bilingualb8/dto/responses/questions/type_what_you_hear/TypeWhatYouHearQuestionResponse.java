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
   private Integer numberOfReplays;
   private Integer questionOrder;
   @Builder.Default
   @Enumerated(EnumType.STRING)
   private QuestionType questionType = QuestionType.TYPE_WHAT_YOU_HEAR;
   private FileResponse file;
   private Long testId;

   public TypeWhatYouHearQuestionResponse(Long id, String title, Integer duration, QuestionType questionType,
                                          // todo file response stuff
                                          Long questionId, Long fileId, FileType fileType,String fileUrl,
                                          // todo ....
                                          Long testId,Integer numberOfReplays,Integer questionOrder) {
      this.id = id;
      this.title = title;
      this.duration = duration;
      this.questionType = questionType;
      this.file = new FileResponse(fileId, fileType, fileUrl, questionId);
      this.testId = testId;
      this.numberOfReplays = numberOfReplays;
      this.questionOrder = questionOrder;
   }
}
