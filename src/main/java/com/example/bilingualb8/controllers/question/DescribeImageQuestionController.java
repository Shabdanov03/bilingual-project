package com.example.bilingualb8.controllers.question;

import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.services.questions.DescribeImageQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/describe-image")
@RequiredArgsConstructor
@CrossOrigin
public class DescribeImageQuestionController {
    private final DescribeImageQuestionService questionService;

    @Operation(summary = "This is save Describe image question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveDescribeQuestion(@RequestBody @Valid DescribeImageQuestionRequest questionRequest) {
        return questionService.saveDescribeQuestion(questionRequest);
    }

    @Operation(summary = "This is update by id Describe image question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateDescribeImageQuestionById(@PathVariable Long id, @RequestBody
    @Valid DescribeImageQuestionUpdateRequest updateRequest) {
        return questionService.updateDescribeImageQuestionById(id, updateRequest);
    }
}
