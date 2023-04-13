package com.example.DOTA.services;

import com.example.DOTA.models.image.ImageClassHero;
import com.example.DOTA.models.image.ImageRasHero;
import com.example.DOTA.repository.image.ImageRepositoryRasHero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class RasHeroService {
    private final ImageRepositoryRasHero imageRepositoryRasHero;
    public void saveRasHero(MultipartFile file1, String name, String detals) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntitySave(file1, name, detals);

        }
    }
    public void updateRasHero(MultipartFile file1, String name, String detals, Long id) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntityUpdate(file1, name, detals, id);
        }
        ImageRasHero imageClassHero = rasHero(id);
        imageClassHero.setName(name);
        imageClassHero.setDetals(detals);
        imageRepositoryRasHero.save(imageClassHero);
    }
    public List<ImageRasHero> listRasHero(){
        return imageRepositoryRasHero.findAll();
    }
    public ImageRasHero rasHero(Long id){
        return imageRepositoryRasHero.findById(id).orElse(null);

    }
    public List<ImageRasHero> findAll(){
        return imageRepositoryRasHero.findAll();
    }
    public int summaClass(){
        return listRasHero().size();
    }
    public void delete(ImageRasHero imageRasHero){
        imageRepositoryRasHero.delete(imageRasHero);
    }

    private ImageRasHero toImageEntitySave(MultipartFile file, String name, String detals) throws IOException {
        ImageRasHero image = new ImageRasHero();
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);
        image.setDetals(detals);
        return imageRepositoryRasHero.save(image);
    }
    private ImageRasHero toImageEntityUpdate(MultipartFile file, String name, String detals, Long id) throws IOException {
        ImageRasHero image = new ImageRasHero();
        image.setId(id);
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);
        image.setDetals(detals);
        return imageRepositoryRasHero.save(image);
    }
}
