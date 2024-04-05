package com.example.microservicedictionary.repository;

import com.example.microservicedictionary.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    Dictionary findByWord(String word);
}
