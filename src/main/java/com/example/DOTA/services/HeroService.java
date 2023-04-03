package com.example.DOTA.services;

import com.example.DOTA.models.Hero;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.repository.HeroRepository;
import com.example.DOTA.repository.image.ImageRepositoryHero;
import com.example.DOTA.services.sort.SortHero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class HeroService {
    private final ImageRepositoryHero imageRepository;
    private final HeroRepository heroRepository;


    public List<Hero> listProducts() {
        return heroRepository.findAll();
    }
    public List<ImageHero> imageHeroList(Hero hero){
        return imageRepository.findByHero(hero);
    }




    public void saveHero(Hero hero, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4) throws IOException {
        if (file1.getSize() != 0) {
           toImageEntity(file1, hero);

        }
        if (file2.getSize() != 0) {
             toImageEntity(file2,hero);


        }
        if (file3.getSize() != 0) {
            toImageEntity(file3,hero);


        }
        if (file4.getSize() != 0) {
             toImageEntity(file4,hero);

        }

    }


    private ImageHero toImageEntity(MultipartFile file, Hero hero) throws IOException {
        ImageHero image = new ImageHero();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setHero(hero);

        heroRepository.save(hero);
        return imageRepository.save(image);
    }
    public Hero getProductById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }





    public List<SortHero> sortHeroes(){

        List<Hero> her = listProducts();

        List<SortHero> heroes = new ArrayList<>();

        for (Hero r : her) {
        List<ImageHero> imgHeroList = imageHeroList(r);
        SortHero sortHero = new SortHero();
        for (ImageHero s : imgHeroList) {

            if (s.getName().equals("iconHero")) {
                sortHero.setIconHero(s.getId());
                sortHero.setHero(s.getHero());
            }
            if (s.getName().equals("iconSpecies")) {
                sortHero.setIconSpecies(s.getId());
                sortHero.setHero(s.getHero());
            }
            if (s.getName().equals("iconClassHero")) {
                sortHero.setIconClassHero(s.getId());
                sortHero.setHero(s.getHero());
            }
            if (s.getName().equals("iconClassHero1")) {
                sortHero.setIconClassHero1(s.getId());
                sortHero.setHero(s.getHero());
            }
        }
        heroes.add(sortHero);
    }
        return heroes;
    }
}
