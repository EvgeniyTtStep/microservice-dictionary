package com.example.microservicedictionary.repository;

import com.example.microservicedictionary.entity.TranslationResultAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslationResultAttemptRepository extends JpaRepository<TranslationResultAttempt, Long> {


    List<TranslationResultAttempt> findTop5ByUserNicknameOrderByIdDesc(String userNickname);

}
