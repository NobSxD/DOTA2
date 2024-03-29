package com.example.DOTA.controller;

import com.example.DOTA.models.Path;
import com.example.DOTA.models.Rasa;
import com.example.DOTA.models.image.*;
import com.example.DOTA.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final HeroService heroService;
    private final SkillService skillService;

    private final EnergisingService energisingService;
    private final PathService pathService;




    @GetMapping("/images/hero/{id}")
    private ResponseEntity<?> getImageByIDHero(@PathVariable Long id) throws IOException {
        ImageHero imageHero = heroService.getImageHero(id);


        return ResponseEntity.ok()
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


    @GetMapping("/images/energising/{id}")
    private ResponseEntity<?> getImageByIDEnergising(@PathVariable Long id){
        ImageEnergising imageEnergising = energisingService.ByIDImageEnergising(id);
        return ResponseEntity.ok()
                .header("fileName", imageEnergising.getOriginalFileName())
                .contentType(MediaType.valueOf(imageEnergising.getContentType()))
                .contentLength(imageEnergising.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageEnergising.getBytes())));
    }
    @GetMapping("/images/path/{id}")
    private ResponseEntity<?> getImageByIDPath(@PathVariable Long id){
        Path path = pathService.pathById(id);
        return ResponseEntity.ok()
                .header("fileName",path.getOriginalFileName())
                .contentType(MediaType.valueOf(path.getContentType()))
                .contentLength(path.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(path.getBytes())));
    }





}
