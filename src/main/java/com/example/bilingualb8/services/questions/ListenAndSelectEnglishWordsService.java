package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.listen_and_select_english_words.ListenAndSelectEnglishWordsRequest;
import com.example.bilingualb8.dto.requests.questions.listen_and_select_english_words.ListenAndSelectEnglishWordsUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public interface ListenAndSelectEnglishWordsService {
    SimpleResponse save(ListenAndSelectEnglishWordsRequest request);

    SimpleResponse update(ListenAndSelectEnglishWordsUpdateRequest updateListenAndSelectRequest, Long id);
}
