package com.example.microservicedictionary.service;

import com.example.microservicedictionary.entity.TranslationResultAttempt;

import java.util.List;

public interface TranslationService {

    String getRandom();

    List<TranslationResultAttempt> getStatsForUser(String nickname);

    boolean checkAttempt(final TranslationResultAttempt attempt);

    TranslationResultAttempt getResultById(Long resultId);
}
