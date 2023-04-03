package com.example.DOTA.services;

import com.example.DOTA.models.ClassHero;
import com.example.DOTA.models.image.ImageClassHero;
import com.example.DOTA.repository.image.ImageRepositoryClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor

public class ClassHeroService {
    private final ImageRepositoryClass imageRepositoryClass;

    public void saveClassHero(ClassHero classHero, MultipartFile file1) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntity(file1, classHero);

        }
    }

    private ImageClassHero toImageEntity(MultipartFile file, ClassHero classHero) throws IOException {
        ImageClassHero image = new ImageClassHero();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setClassHero(classHero);
        return imageRepositoryClass.save(image);
    }
}
