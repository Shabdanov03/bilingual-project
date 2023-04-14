package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "TEST IS WORKING!";
    }
}
