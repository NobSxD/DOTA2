package com.example.DOTA.repository.image;

import com.example.DOTA.models.Hero;
import com.example.DOTA.models.image.ImageHero;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepositoryHero extends JpaRepository<ImageHero, Long> {
    List<ImageHero> findByHero(Hero hero);


}
