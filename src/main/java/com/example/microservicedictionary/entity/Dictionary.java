package com.example.microservicedictionary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DICTIONARY_ID")
    private Long id;
    private final String word;
    private final String translate;

    public Dictionary() {
        this(null, null);
    }

    public Dictionary(String word, String translate) {
        this.word = word;
        this.translate = translate;
    }
}
