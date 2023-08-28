package com.example.DOTA.models;

import com.example.DOTA.models.image.ImageHero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


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
    private String max_xp;
    private String max_mp;
    private String damage;
    private String speed_damage;
    private String range_attack;
    private String armor;
    private String mag_resist;
    private String name_skill;
    private String activation_skill;

    private String full_text;
    private String detals_skill1;
    private String detals_skill2;
    private String detals_skill3;
    private String detals_skill4;
    private String detals_skill5;
    private String detals_skill6;
    private String cast_mp;
    private String kd_skill;

    private LocalDateTime dateTime;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Energising energising;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Energising energising1;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Energising energising2;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageHero> imageHero;




    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

}
