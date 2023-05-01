package com.example.DOTA.models;

import com.example.DOTA.models.image.ImageHero;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToOne(cascade = CascadeType.PERSIST)
    private Energising energising;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Energising energising1;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Energising energising2;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ImageHero imageHero;




    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

}
