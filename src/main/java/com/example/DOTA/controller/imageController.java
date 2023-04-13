package com.example.DOTA.controller;

import com.example.DOTA.models.image.ImageClassHero;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.models.image.ImageRasHero;
import com.example.DOTA.repository.image.ImageRepositoryClass;
import com.example.DOTA.repository.image.ImageRepositoryHero;
import com.example.DOTA.repository.image.ImageRepositoryRasHero;
import com.example.DOTA.services.HeroService;
import com.example.DOTA.services.RasHeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class imageController {
    private final ImageRepositoryHero imageRepositoryHero;
    private final ImageRepositoryClass imageRepositoryClass;

    private final RasHeroService rasHeroService;

    @GetMapping("/images/hero/{id}")
    private ResponseEntity<?> getImageByIDHero(@PathVariable Long id){
        ImageHero imageHero = imageRepositoryHero.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", imageHero.getOriginalFileName())
                .contentType(MediaType.valueOf(imageHero.getContentType()))
                .contentLength(imageHero.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageHero.getBytes())));
    }

    @GetMapping("/images/class/{id}")
    private ResponseEntity<?> getImageByIDClass(@PathVariable Long id){
        ImageClassHero imageHero = imageRepositoryClass.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", imageHero.getOriginalFileName())
                .contentType(MediaType.valueOf(imageHero.getContentType()))
                .contentLength(imageHero.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageHero.getBytes())));
    }
    @GetMapping("/images/ras/{id}")
    private ResponseEntity<?> getImageByIDRas(@PathVariable Long id){
        ImageRasHero imageHero = rasHeroService.rasHero(id);
        return ResponseEntity.ok()
                .header("fileName", imageHero.getOriginalFileName())
                .contentType(MediaType.valueOf(imageHero.getContentType()))
                .contentLength(imageHero.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageHero.getBytes())));
    }
}
