package com.example.microservicedictionary.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class TranslationCompletedEvent implements Serializable {

    private final Long translationResultAttemptId;
    private final Long userId;
    private final boolean correct;



}
