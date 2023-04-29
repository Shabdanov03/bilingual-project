package com.example.bilingualb8.controllers.question;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.describe_image.DescribeImageQuestionResponse;
import com.example.bilingualb8.services.questions.DescribeImageQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/describe-image")
@RequiredArgsConstructor
public class DescribeImageQuestionController {
    private final DescribeImageQuestionService questionService;
    @PostMapping("/save")
    public SimpleResponse saveDescribeQuestion(@RequestBody @Valid DescribeImageQuestionRequest questionRequest) {
        return questionService.saveDescribeQuestion(questionRequest);
    }
    @GetMapping("/all")
    public List<DescribeImageQuestionResponse> getAllDescribeImageQuestion(){
        return questionService.getAllDescribeImageQuestion();
    }
    @GetMapping("/{id}")
    public DescribeImageQuestionResponse getDescribeImageQuestionById(@PathVariable Long id){
        return questionService.getDescribeImageQuestionById(id);
    }
    @DeleteMapping("/{id}/delete")
    public SimpleResponse deleteDescribeImageQuestionById(@PathVariable Long id){
        return questionService.deleteDescribeImageQuestionById(id);
    }
    @PutMapping("/{id}/update")
    public SimpleResponse updateDescribeImageQuestionById(@PathVariable Long id, @RequestBody
    @Valid DescribeImageQuestionUpdateRequest updateRequest){
        return questionService.updateDescribeImageQuestionById(id,updateRequest);
    }


}
