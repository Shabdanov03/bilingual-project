package com.example.bilingualb8.controllers;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.userResult.MyResultResponse;
import com.example.bilingualb8.services.MyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
@CrossOrigin
public class MyResultsController {

    private final MyResultService myResultService;

    @GetMapping("/{userId}")
    public List<MyResultResponse> getAll(@PathVariable Long userId){
        return myResultService.getAll(userId);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id){
        return myResultService.deleteById(id);
    }
}
