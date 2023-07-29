package com.example.DOTA.repository;

import com.example.DOTA.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    List<Hero> findByTirHero(String tirHero);
    List<Hero> findByClassHero(String classHero);
    List<Hero> findByClassHero1(String classHero1);
    List<Hero> findByClassHero2(String classHero2);
}
