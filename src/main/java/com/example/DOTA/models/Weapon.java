package com.example.DOTA.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "weapon")
@NoArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;                                                                                             //сборка
    private String tirWeapon;
    private String fullText;

    public Weapon(String name, String species, String tirWeapon, String fullText) {
        this.name = name;
        this.species = species;
        this.tirWeapon = tirWeapon;
        this.fullText = fullText;
    }
}
