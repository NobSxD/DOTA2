package com.example.DOTA.repository.image;

import com.example.DOTA.models.image.ImageClassHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepositoryClass extends JpaRepository<ImageClassHero, Long> {
        List<ImageClassHero> findByName(String name);
}
