package com.example.bilingualb8.services.questions;
import com.example.bilingualb8.dto.requests.questions.select_the_main_idea.SelectTheMainIdeaQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.select_the_main_idea.SelectTheMainIdeaQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface SelectTheMainIdeaQuestionService {
    SimpleResponse saveSelectTheMainIdeaQuestion(SelectTheMainIdeaQuestionRequest request);
    SimpleResponse updateSelectTheMainQuestionById(Long id,SelectTheMainIdeaQuestionUpdateRequest request);

}
