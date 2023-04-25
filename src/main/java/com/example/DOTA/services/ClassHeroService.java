package com.example.DOTA.services;

import com.example.DOTA.models.image.ImageClassHero;
import com.example.DOTA.repository.image.ImageRepositoryClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ClassHeroService {
    private final ImageRepositoryClass imageRepositoryClass;

    public void saveClassHero(MultipartFile file1, String name, String detals) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntitySave(file1, name, detals);

        }
    }
    public void updateClassHero(MultipartFile file1, String name, String detals, Long id) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntityUpdate(file1, name, detals, id);
        }
        ImageClassHero imageClassHero = classHero(id);
        imageClassHero.setName(name);
        imageClassHero.setDetals(detals);
        imageRepositoryClass.save(imageClassHero);
    }
    public List<ImageClassHero> listClassHero(){
        return imageRepositoryClass.findAll();
    }
    public ImageClassHero  classHero(Long id){
       return imageRepositoryClass.findById(id).orElse(null);

    }
    public List<ImageClassHero> findAll(){
        return imageRepositoryClass.findAll();
    }
    public long summaClass(){
        return imageRepositoryClass.count();
    }
    public void delete(ImageClassHero imageClassHero){
        imageRepositoryClass.delete(imageClassHero);
    }

    private ImageClassHero toImageEntitySave(MultipartFile file, String name, String detals) throws IOException {
        ImageClassHero image = new ImageClassHero();
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);
        image.setDetals(detals);
        return imageRepositoryClass.save(image);
    }
    private ImageClassHero toImageEntityUpdate(MultipartFile file, String name, String detals, Long id) throws IOException {
        ImageClassHero image = new ImageClassHero();
        image.setId(id);
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);
        image.setDetals(detals);
        return imageRepositoryClass.save(image);
    }
}
