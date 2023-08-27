package com.example.DOTA.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "weapon")
@NoArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String tirWeapon;
    private String passiveSkill;
    private String activeSkill;
    private String rangeAttack;
    private String kd;



    private String icon;
    private LocalDateTime dateTime;

    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }




}



