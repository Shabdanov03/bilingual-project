package com.example.bilingualb8.controllers;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.services.ScoreSender;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sendEmail")
@RequiredArgsConstructor
@CrossOrigin
public class ScoreSenderController {

    private final ScoreSender scoreSender;

    @PostMapping("/{resultId}")
    public SimpleResponse sendEmail(@Valid @PathVariable Long resultId) {
         return scoreSender.scoreSender(resultId);
    }
}

