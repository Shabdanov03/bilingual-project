package com.example.bilingualb8.controllers.question;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.describe_image.DescribeImageQuestionResponse;
import com.example.bilingualb8.services.questions.DescribeImageQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/describe-image")
@RequiredArgsConstructor
@CrossOrigin
public class DescribeImageQuestionController {
    private final DescribeImageQuestionService questionService;
    @Operation(summary = "This is save Describe image question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public SimpleResponse saveDescribeQuestion(@RequestBody @Valid DescribeImageQuestionRequest questionRequest) {
        return questionService.saveDescribeQuestion(questionRequest);
    }
    @Operation(summary = "This is get all Describe image question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/all")
    public List<DescribeImageQuestionResponse> getAllDescribeImageQuestion(){
        return questionService.getAllDescribeImageQuestion();
    }
    @Operation(summary = "This is get by id  Describe image question method")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public DescribeImageQuestionResponse getDescribeImageQuestionById(@PathVariable Long id){
        return questionService.getDescribeImageQuestionById(id);
    }
    @Operation(summary = "This is delete by id Describe image question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteDescribeImageQuestionById(@PathVariable Long id){
        return questionService.deleteDescribeImageQuestionById(id);
    }
    @Operation(summary = "This is update by id Describe image question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/update")
    public SimpleResponse updateDescribeImageQuestionById(@PathVariable Long id, @RequestBody
    @Valid DescribeImageQuestionUpdateRequest updateRequest){
        return questionService.updateDescribeImageQuestionById(id,updateRequest);
    }


}
