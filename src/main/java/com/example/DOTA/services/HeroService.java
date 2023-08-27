package com.example.DOTA.services;

import com.example.DOTA.models.Energising;
import com.example.DOTA.models.Hero;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.repository.HeroRepository;
import com.example.DOTA.repository.image.ImageRepositoryHero;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor


public class HeroService {

    private final ImageRepositoryHero imageRepository;


    private final HeroRepository heroRepository;
    private final EnergisingService energisingService;
    public ImageHero getImageHero(Long id){
        return imageRepository.findById(id).orElse(null);
    }



    public boolean heroExistById(Long id) {
        return heroRepository.existsById(id);
    }

    public List<Hero> getListHeroAll(){
        return heroRepository.findAll();
    }


    public Hero getHeroById(Long id) {
        log.info("getting user by id: {}", id);
        return
                heroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
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

    public List<ImageHero> imageHeroAll() {
        return imageRepository.findAll();
    }

    public ImageHero saveImage(ImageHero imageHero) {
        return imageRepository.save(imageHero);
    }


    public List<Hero> displayHeroEnergising(String nameEnergising){
        List<Hero> heroList1 = heroRepository.findByClassHero(nameEnergising);
        List<Hero> heroList2 = heroRepository.findByClassHero1(nameEnergising);
        List<Hero> heroList3 = heroRepository.findByClassHero2(nameEnergising);
        List<Hero> displayHero = Stream.concat(heroList1.stream(),heroList2.stream()).collect(Collectors.toList());
        displayHero = Stream.concat(displayHero.stream(),heroList3.stream()).collect(Collectors.toList());
        return displayHero;
    }

    public List<Hero> displayHeroEnergising(Long id){
        Energising energising = energisingService.ByIdEnergising(id);
        return displayHeroEnergising(energising.getName());
    }




    //сохраняеим героя и делаем привязку к иконки героя и к расам и классам
    public void saveHero(Hero hero,
                         MultipartFile file1,
                         MultipartFile file2) throws IOException {
        List<Energising> energisingList = new ArrayList<>();
        energisingList.add(energisingService.energisingName(hero.getClassHero()));
        energisingList.add(energisingService.energisingName(hero.getClassHero1()));
        energisingList.add(energisingService.energisingName(hero.getClassHero2()));

        Hero heroAdd = hero;
        List<ImageHero> imageHeroList = new ArrayList<>();

        hero.setDateTime(LocalDateTime.now());

        hero.setImageHero(imageHeroList);
        if (file1.getSize() > 0) {
            saveImage(toImageEntity(heroAdd.getNameHero(), file1));
            imageHeroList.add(toImageEntity(heroAdd.getNameHero(), file1));

        }
        if (file2.getSize() > 0) {
            saveImage(toImageEntity(heroAdd.getNameHero(), file2));
            imageHeroList.add(toImageEntity(heroAdd.getNameHero(), file2));
        }


        if (energisingList.size() >= 1) {
            hero.setEnergising(energisingList.get(0));
        }
        if (energisingList.size() >= 2) {
            hero.setEnergising1(energisingList.get(1));
        }
        if (energisingList.size() >= 3) {
            hero.setEnergising2(energisingList.get(2));
        }
        saveHero(hero);


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

    public void editHero(Hero heroGet,
                         MultipartFile file1,
                         MultipartFile file2) throws IOException {
        Hero hero = getHeroById(heroGet.getId());
        List<ImageHero> imageHeroList = hero.getImageHero();
        List<Energising> energisingList = new ArrayList<>();
        energisingList.add(energisingService.energisingName(heroGet.getClassHero()));
        energisingList.add(energisingService.energisingName(heroGet.getClassHero1()));
        energisingList.add(energisingService.energisingName(heroGet.getClassHero2()));


        hero.setNameHero(heroGet.getNameHero());
        hero.setTirHero(heroGet.getTirHero());
        hero.setClassHero(heroGet.getClassHero());
        hero.setClassHero1(heroGet.getClassHero1());
        hero.setClassHero2(heroGet.getClassHero2());
        hero.setMax_xp(heroGet.getMax_xp());
        hero.setMax_mp(heroGet.getMax_mp());
        hero.setDamage(heroGet.getDamage());
        hero.setSpeed_damage(heroGet.getSpeed_damage());
        hero.setRange_attack(heroGet.getRange_attack());
        hero.setArmor(heroGet.getArmor());
        hero.setMag_resist(heroGet.getMag_resist());
        hero.setName_skill(heroGet.getName_skill());
        hero.setActivation_skill(heroGet.getActivation_skill());
        hero.setFull_text(heroGet.getFull_text());
        hero.setDateTime(LocalDateTime.now());
        hero.setDetals_skill1(heroGet.getDetals_skill1());
        hero.setDetals_skill2(heroGet.getDetals_skill2());
        hero.setDetals_skill3(heroGet.getDetals_skill3());
        hero.setDetals_skill4(heroGet.getDetals_skill4());
        hero.setDetals_skill5(heroGet.getDetals_skill5());
        hero.setDetals_skill6(heroGet.getDetals_skill6());
        hero.setCast_mp(heroGet.getCast_mp());
        hero.setKd_skill(heroGet.getKd_skill());
        hero.setImageHero(imageHeroList);
        if (file1.getSize() > 0) {
            saveImage(editToImageEntity(file1, hero.getImageHero().get(0).getId()));
            imageHeroList.set(0, editToImageEntity(file1, hero.getImageHero().get(0).getId()));

        }
        if (file2.getSize() > 0) {
            saveImage(editToImageEntity(file2, hero.getImageHero().get(1).getId()));
            imageHeroList.set(1, editToImageEntity(file2, hero.getImageHero().get(1).getId()));
        }


        if (energisingList.size() >= 1) {
            hero.setEnergising(energisingList.get(0));
        }
        if (energisingList.size() >= 2) {
            hero.setEnergising1(energisingList.get(1));
        }
        if (energisingList.size() >= 3) {
            hero.setEnergising2(energisingList.get(2));
        }
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



    @Cacheable("displayHeroUser")
    public List<Hero> displayHero(String tirHero) {
        List<Hero> heroList = heroRepository.findByTirHero(tirHero);
                  
        return heroList;
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
