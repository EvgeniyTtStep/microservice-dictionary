package com.example.microservicedictionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class TranslationResultAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private final User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "WORD_ID")
    private final Dictionary dictionary;

    private final String resultAttempt;

    private final boolean correct;


    public TranslationResultAttempt() {
        this(null, null, null, false);
    }

    public TranslationResultAttempt(User user, Dictionary dictionary, String resultAttempt, boolean correct) {
        this.user = user;
        this.dictionary = dictionary;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }
}
