package com.example.DOTA.controller;

import com.example.DOTA.models.image.ImageClassHero;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.models.image.ImageRasHero;
import com.example.DOTA.models.image.ImageSkill;
import com.example.DOTA.repository.image.ImageRepositoryClass;
import com.example.DOTA.repository.image.ImageRepositoryHero;
import com.example.DOTA.repository.image.ImageRepositoryRasHero;
import com.example.DOTA.services.ClassHeroService;
import com.example.DOTA.services.HeroService;
import com.example.DOTA.services.RasHeroService;
import com.example.DOTA.services.SkillService;
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
    private final ClassHeroService classHeroService;

    private final RasHeroService rasHeroService;

    private final SkillService skillService;

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
        ImageClassHero imageHero = classHeroService.classHero(id);
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
    @GetMapping("/images/skill/{id}")
    private ResponseEntity<?> getImageByIDSkill(@PathVariable Long id){
        ImageSkill imageSkill = skillService.skill(id);
        return ResponseEntity.ok()
                .header("fileName", imageSkill.getOriginalFileName())
                .contentType(MediaType.valueOf(imageSkill.getContentType()))
                .contentLength(imageSkill.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageSkill.getBytes())));
    }
}
