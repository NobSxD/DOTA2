package com.example.DOTA.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rasHero")

public class RasHero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;

    public RasHero(String name, String species) {
        this.name = name;
        this.species = species;
    }
}
