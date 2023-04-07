package com.example.DOTA.services.sort;

import com.example.DOTA.models.Hero;
import lombok.Data;

@Data
public class SortHero {
    Hero hero = new Hero();
    Long iconHero;
    Long iconClassHero;
    Long iconClassHero1;
    Long iconClassHero2;
}
