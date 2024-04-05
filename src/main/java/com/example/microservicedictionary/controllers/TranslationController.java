package com.example.microservicedictionary.controllers;


import com.example.microservicedictionary.entity.TranslationResultAttempt;
import com.example.microservicedictionary.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TranslationController {
    @Autowired
    private TranslationService translationService;


    @GetMapping("/results")
    ResponseEntity<List<TranslationResultAttempt>> getStatistics(@RequestParam("nickname") String nickname) {
        return ResponseEntity.ok(translationService.getStatsForUser(nickname));
    }

    @GetMapping("/translations/random")
    String getRandom() {
        return translationService.getRandom();
    }

    @PostMapping("/results")
    ResponseEntity<TranslationResultAttempt> postResult(@RequestBody TranslationResultAttempt translationResultAttempt) {
        boolean isCorrect = translationService.checkAttempt(translationResultAttempt);
        TranslationResultAttempt attemptCopy = new TranslationResultAttempt(
                translationResultAttempt.getUser(),
                translationResultAttempt.getDictionary(),
                translationResultAttempt.getResultAttempt(),
                isCorrect
        );
        return ResponseEntity.ok(attemptCopy);
    }
}
