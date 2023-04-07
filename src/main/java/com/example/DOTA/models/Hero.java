package com.example.DOTA.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "hero")

public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameHero;
    private String tirHero;
    private String classHero;
    private String classHero1;
    private String classHero2;

    private String full_text;

    private LocalDateTime dateTime;




    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

    public Hero(String nameHero, String tirHero, String classHero, String classHero1, String classHero2, String full_text) {
        this.nameHero = nameHero;
        this.tirHero = tirHero;
        this.classHero = classHero;
        this.classHero1 = classHero1;
        this.classHero2 = classHero2;
        this.full_text = full_text;
    }
}
