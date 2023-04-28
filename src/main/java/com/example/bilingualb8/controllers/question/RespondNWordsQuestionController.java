package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.question.respondNWords.RespondNWordsQuestionRequest;
import com.example.bilingualb8.dto.requests.question.respondNWords.RespondNWordsQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.question.RespondNWordsQuestionResponse;
import com.example.bilingualb8.services.questions.RespondNWordsQuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/respond-n-words")
public class RespondNWordsQuestionController {
    private final RespondNWordsQuestionService respondNWordsQuestionService;

    @Autowired
    public RespondNWordsQuestionController(RespondNWordsQuestionService respondNWordsQuestionService) {
        this.respondNWordsQuestionService = respondNWordsQuestionService;
    }

    @PostMapping("/save")
    public SimpleResponse respondNWordsSave(@RequestBody @Valid RespondNWordsQuestionRequest request) {
        return respondNWordsQuestionService.respondNWordsSave(request);
    }

    @GetMapping("/all")
    public List<RespondNWordsQuestionResponse> getAllRespondNWords() {
        return respondNWordsQuestionService.getAllRespondNWords();
    }

    @GetMapping("/{id}")
    public RespondNWordsQuestionResponse findByIdRespondNWords(@PathVariable Long id) {
        return respondNWordsQuestionService.findByIdRespondNWords(id);
    }

    @PostMapping("/{id}/delete")
    public SimpleResponse deleteByIdRespondNWords(@PathVariable Long id) {
        return respondNWordsQuestionService.deleteByIdRespondNWords(id);
    }

    @PutMapping("/{id}/update")
    public SimpleResponse updateRespondNWords(
            @PathVariable Long id, @RequestBody @Valid RespondNWordsQuestionUpdateRequest request) {
        return respondNWordsQuestionService.updateRespondNWords(id, request);
    }
}
