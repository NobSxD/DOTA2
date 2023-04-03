package com.example.DOTA.models;

import com.example.DOTA.models.image.ImageHero;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "hero")

public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameHero;
    private String species;
    private String classHero;
    private String classHero2;
    private String classHero3;
    private String tirHero;

    private LocalDateTime dateTime;




    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

    public Hero(String nameHero, String species, String classHero, String classHero2, String classHero3, String tirHero) {
        this.nameHero = nameHero;
        this.species = species;
        this.classHero = classHero;
        this.classHero2 = classHero2;
        this.classHero3 = classHero3;
        this.tirHero = tirHero;
    }
}
