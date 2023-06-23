package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Result;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.entity.UserInfo;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.ResultRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.repositories.UserInfoRepository;
import com.example.bilingualb8.services.EmailService;
import com.example.bilingualb8.services.ScoreSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreSenderImpl implements ScoreSender {
    private final TestRepository testRepository;
    private final TemplateEngine templateEngine;
    private final EmailService emailService;
    private final ResultRepository resultRepository;
    private final UserInfoRepository userInfoRepository;

    @Override
    public SimpleResponse scoreSender(Long resultId) {
        log.info("Initiating password reset");
        try {
            Result result = resultRepository.findById(resultId)
                    .orElseThrow(() -> new NotFoundException(String.format("Result with ID %d not found", resultId)));

            UserInfo userInfo = userInfoRepository.findByUserId(result.getUser().getId())
                    .orElseThrow(()-> new NotFoundException(String.format("User info with ID %d not found", result.getUser().getId())));

            Test test = testRepository.findById(result.getTest().getId())
                    .orElseThrow(() -> new NotFoundException(String.format("Test with ID %d not found", result.getTest().getId())));

            String subject = "Password Reset Request";

            Context context = new Context();
            context.setVariable("name",String.format("Привет, %s!", userInfo.getUser().getFirstName()));
            context.setVariable("title",String.format("Ваш тест %s был успешно проверен администрацией.", test.getTitle()) );

            String htmlContent = templateEngine.process("result.html", context);

            emailService.sendEmail(userInfo.getEmail(), subject, htmlContent);
            log.info("Password reset email sent");

            return SimpleResponse.builder()
                    .message("The password reset was sent to your email. Please check your email.")
                    .build();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

