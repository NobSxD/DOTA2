package com.example.DOTA.repository.image;

import com.example.DOTA.models.image.ImageClassHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepositoryClass extends JpaRepository<ImageClassHero, Long> {
}
