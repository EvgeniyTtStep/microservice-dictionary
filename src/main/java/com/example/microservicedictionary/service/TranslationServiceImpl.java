package com.example.microservicedictionary.service;

import com.example.microservicedictionary.entity.Dictionary;
import com.example.microservicedictionary.entity.TranslationResultAttempt;
import com.example.microservicedictionary.entity.User;
import com.example.microservicedictionary.event.EventDispatcher;
import com.example.microservicedictionary.event.TranslationCompletedEvent;
import com.example.microservicedictionary.repository.DictionaryRepository;
import com.example.microservicedictionary.repository.TranslationResultAttemptRepository;
import com.example.microservicedictionary.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventDispatcher eventDispatcher;

    @Autowired
    private TranslationResultAttemptRepository attemptRepository;

    @Override
    public String getRandom() {
        List<Dictionary> words = new ArrayList<>();
        dictionaryRepository.findAll().iterator().forEachRemaining(words::add);
        int index = new Random().nextInt(words.size());
        return words.get(index).getWord();
    }

    @Override
    public List<TranslationResultAttempt> getStatsForUser(final String userNickname) {
        return attemptRepository.findTop5ByUserNicknameOrderByIdDesc(userNickname);
    }

//    @Override
//    public TranslationResultAttempt getResultById(final Long resultId) {
//        return attemptRepository.findById(resultId).orElse(null);
//    }


    @Transactional
    @Override
    public boolean checkAttempt(final TranslationResultAttempt attempt) {
        Optional<User> user = userRepository
                .findByNickname(attempt.getUser().getNickname());

        Dictionary word = dictionaryRepository
                .findByWord(attempt.getDictionary().getWord());

        Assert.isTrue(!attempt.isCorrect(), "You can't send an attempt marked as correct!");

        boolean isCorrect = word.getTranslate()
                .equals(attempt.getResultAttempt());

        TranslationResultAttempt checkedAttempt = new TranslationResultAttempt(
                user.orElse(attempt.getUser()),
                word,
                attempt.getResultAttempt(),
                isCorrect
        );
        attemptRepository.save(checkedAttempt);
        eventDispatcher.send(
                new TranslationCompletedEvent(checkedAttempt.getId(),
                        checkedAttempt.getUser().getId(),
                        checkedAttempt.isCorrect())
        );

        return isCorrect;
    }



}
