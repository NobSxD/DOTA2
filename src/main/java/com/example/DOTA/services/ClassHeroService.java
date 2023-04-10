package com.example.DOTA.services;

import com.example.DOTA.models.image.ImageClassHero;
import com.example.DOTA.repository.image.ImageRepositoryClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ClassHeroService {
    private final ImageRepositoryClass imageRepositoryClass;

    public void saveClassHero(MultipartFile file1, String name, String detals) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntity(file1, name, detals);

        }
    }
    public List<ImageClassHero> listClassHero(){
        return imageRepositoryClass.findAll();
    }
    public ImageClassHero  classHero(Long id){
       return imageRepositoryClass.findById(id).orElse(null);

    }
    public void delete(ImageClassHero imageClassHero){
        imageRepositoryClass.delete(imageClassHero);
    }

    private ImageClassHero toImageEntity(MultipartFile file, String name, String detals) throws IOException {
        ImageClassHero image = new ImageClassHero();
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);
        image.setDetals(detals);
        return imageRepositoryClass.save(image);
    }
}
