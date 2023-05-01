package com.example.DOTA.services;

import com.example.DOTA.models.Energising;
import com.example.DOTA.models.Hero;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.repository.HeroRepository;
import com.example.DOTA.repository.image.ImageRepositoryHero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class HeroService {

    private final ImageRepositoryHero imageRepository;
    private final HeroRepository heroRepository;
    private final EnergisingService energisingService;


    public List<Hero> listHero() {
        return heroRepository.findAll();
    }

    public boolean heroExistById(Long id) {
        return heroRepository.existsById(id);
    }


    public Hero getHeroById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public long summaHero() {
        return heroRepository.count();
    }

    public List<ImageHero> imageHeroList(Hero hero) {
        return imageRepository.findByHero(hero);
    }

    public ImageHero findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public ImageHero saveImage(ImageHero imageHero) {
        return imageRepository.save(imageHero);
    }


    //сохраняеим героя и делаем привязку к иконки героя и к расам и классам
    public void saveHero(String name,
                         String tear,
                         String classHero,
                         String classHero1,
                         String classHero2,
                         String full_text,
                         MultipartFile file1) throws IOException {
        List<Energising> energisingList = new ArrayList<>();
        energisingList.add(energisingService.energisingName(classHero));
        energisingList.add(energisingService.energisingName(classHero1));
        energisingList.add(energisingService.energisingName(classHero2));

        Hero hero = new Hero();
        saveImage(toImageEntity(name, file1));
        if (file1.getSize() != 0) {
            hero.setNameHero(name);
            hero.setTirHero(tear);
            hero.setClassHero(classHero);
            hero.setClassHero1(classHero1);
            hero.setClassHero2(classHero2);
            hero.setFull_text(full_text);
            hero.setDateTime(LocalDateTime.now());
            hero.setImageHero(toImageEntity(name, file1));
            if (energisingList.size()>=1){hero.setEnergising(energisingList.get(0));}
            if (energisingList.size()>=2){hero.setEnergising1(energisingList.get(1));}
            if (energisingList.size()>=3){hero.setEnergising2(energisingList.get(2));}
            saveHero(hero);


        }

    }

    private ImageHero toImageEntity(String name, MultipartFile file1) throws IOException {
        ImageHero image = new ImageHero();
        image.setName(name);
        image.setOriginalFileName(file1.getOriginalFilename());
        image.setSize(file1.getSize());
        image.setContentType(file1.getContentType());
        image.setBytes(file1.getBytes());

        return image;
    }

    public void editHero(Long id,
                         String name,
                         String tear,
                         String classHero,
                         String classHero1,
                         String classHero2,
                         String full_text,
                         MultipartFile file1) throws IOException {

        List<Energising> energisingList = new ArrayList<>();
        energisingList.add(energisingService.energisingName(classHero));
        energisingList.add(energisingService.energisingName(classHero1));
        energisingList.add(energisingService.energisingName(classHero2));

        Hero hero = heroRepository.findById(id).orElse(null);

            hero.setNameHero(name);
            hero.setTirHero(tear);
            hero.setClassHero(classHero);
            hero.setClassHero1(classHero1);
            hero.setClassHero2(classHero2);
            hero.setFull_text(full_text);
            hero.setDateTime(LocalDateTime.now());
        if (file1.getSize() != 0) {
            hero.setImageHero(editToImageEntity(file1, id));
        }
            if (energisingList.size()>=1){hero.setEnergising(energisingList.get(0));}
            if (energisingList.size()>=2){hero.setEnergising1(energisingList.get(1));}
            if (energisingList.size()>=3){hero.setEnergising2(energisingList.get(2));}
            saveHero(hero);


    }


    private ImageHero editToImageEntity(MultipartFile file, Long id) throws IOException {
        ImageHero image = new ImageHero();
        image.setId(id);
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());

        return image;
    }


    public void displayHero(List<Hero> heroTir1, List<Hero> heroTir2, List<Hero> heroTir3, List<Hero> heroTir4, List<Hero> heroTir5, List<Hero> heroTir6) {
        List<Hero> heroList = listHero();
        for (Hero sortTir : heroList) {
            if (sortTir.getTirHero().equals("ТИР 1") &&                                                                     //сортируем героев по тиру, ну в случаи есла админ указал не верный тир
                    !sortTir.getTirHero().equals("ТИР 2") &&                                                                // то выводим это через 6 тир для админа, что бы была возможность исправить тир героя
                    !sortTir.getTirHero().equals("ТИР 3") &&
                    !sortTir.getTirHero().equals("ТИР 4") &&
                    !sortTir.getTirHero().equals("ТИР 5")) {

                if (sortTir.getTirHero().equals("ТИР 1")) {
                    heroTir1.add(sortTir);
                }
                if (sortTir.getTirHero().equals("ТИР 2")) {
                    heroTir2.add(sortTir);
                }
                if (sortTir.getTirHero().equals("ТИР 3")) {
                    heroTir3.add(sortTir);
                }
                if (sortTir.getTirHero().equals("ТИР 4")) {
                    heroTir4.add(sortTir);
                }
                if (sortTir.getTirHero().equals("ТИР 5")) {
                    heroTir5.add(sortTir);
                }
            } else heroTir6.add(sortTir);
        }
    }

    public void deleteHero(Long id) {
        Hero hero = getHeroById(id);
        List<ImageHero> imageHeroList = imageHeroList(hero);

        for (ImageHero delete : imageHeroList
        ) {
            imageRepository.delete(delete);
        }

        heroRepository.delete(hero);

    }
}
