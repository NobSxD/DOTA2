package com.example.DOTA.controller;

import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.repository.image.ImageRepositoryHero;
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
public class imageControllerHero {
    private final ImageRepositoryHero imageRepositoryHero;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageByID(@PathVariable Long id){
        ImageHero imageHero = imageRepositoryHero.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", imageHero.getOriginalFileName())
                .contentType(MediaType.valueOf(imageHero.getContentType()))
                .contentLength(imageHero.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageHero.getBytes())));
    }
}
