package com.example.DOTA.services;

import com.example.DOTA.models.RasHero;
import com.example.DOTA.models.image.ImageRasHero;
import com.example.DOTA.repository.image.ImageRepositoryRasHero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
@Slf4j
@RequiredArgsConstructor

public class RasHeroService {
    private final ImageRepositoryRasHero imageRepositoryRasHero;
    public void saveClassHero(RasHero rasHero, MultipartFile file1) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntity(file1, rasHero);

        }
    }

    private ImageRasHero toImageEntity(MultipartFile file, RasHero rasHero) throws IOException {
        ImageRasHero image = new ImageRasHero();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setRasHero(rasHero);
        return imageRepositoryRasHero.save(image);
    }
}
