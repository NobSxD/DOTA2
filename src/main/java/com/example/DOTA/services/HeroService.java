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
    public void saveHero(String nameHero,
                            String tirHero,
                            String classHero,
                            String classHero1,
                            String classHero2,
                            String max_xp,
                            String max_mp,
                            String damage,
                            String speed_damage,
                            String range_attack,
                            String armor,
                            String mag_resist,
                            String name_skill,
                            String activation_skill,

                            String full_text,
                            String detals_skill1,
                            String detals_skill2,
                            String detals_skill3,
                            String detals_skill4,
                            String detals_skill5,
                            String detals_skill6,
                            String cast_mp,
                            String kd_skill,
                            MultipartFile file1,
                            MultipartFile file2) throws IOException {
        List<Energising> energisingList = new ArrayList<>();
        energisingList.add(energisingService.energisingName(classHero));
        energisingList.add(energisingService.energisingName(classHero1));
        energisingList.add(energisingService.energisingName(classHero2));

        Hero hero = new Hero();
        List<ImageHero> imageHeroList = new ArrayList<>();

            hero.setNameHero(nameHero);
            hero.setTirHero(tirHero);
            hero.setClassHero(classHero);
            hero.setClassHero1(classHero1);
            hero.setClassHero2(classHero2);
            hero.setMax_xp(max_xp);
            hero.setMax_mp(max_mp);
            hero.setDamage(damage);
            hero.setSpeed_damage(speed_damage);
            hero.setRange_attack(range_attack);
            hero.setArmor(armor);
            hero.setMag_resist(mag_resist);
            hero.setName_skill(name_skill);
            hero.setActivation_skill(activation_skill);
            hero.setFull_text(full_text);
            hero.setDateTime(LocalDateTime.now());
            hero.setDetals_skill1(detals_skill1);
            hero.setDetals_skill2(detals_skill2);
            hero.setDetals_skill3(detals_skill3);
            hero.setDetals_skill4(detals_skill4);
            hero.setDetals_skill5(detals_skill5);
            hero.setDetals_skill6(detals_skill6);
            hero.setCast_mp(cast_mp);
            hero.setKd_skill(kd_skill);
            hero.setImageHero(imageHeroList);
        if(file1.getSize() > 0){
            saveImage(toImageEntity(nameHero, file1));
            imageHeroList.add(toImageEntity(nameHero,file1));

        }
        if(file2.getSize() > 0){
            saveImage(toImageEntity(nameHero, file2));
            imageHeroList.add(toImageEntity(nameHero,file2));
        }


            if (energisingList.size()>=1){hero.setEnergising(energisingList.get(0));}
            if (energisingList.size()>=2){hero.setEnergising1(energisingList.get(1));}
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

    public void editHero(Long id,
                         String nameHero,
                         String tirHero,
                         String classHero,
                         String classHero1,
                         String classHero2,
                         String max_xp,
                         String max_mp,
                         String damage,
                         String speed_damage,
                         String range_attack,
                         String armor,
                         String mag_resist,
                         String name_skill,
                         String activation_skill,

                         String full_text,
                         String detals_skill1,
                         String detals_skill2,
                         String detals_skill3,
                         String detals_skill4,
                         String detals_skill5,
                         String detals_skill6,
                         String cast_mp,
                         String kd_skill,
                         MultipartFile file1,
                         MultipartFile file2) throws IOException {
        Hero hero = getHeroById(id);
        List<ImageHero> imageHeroList = hero.getImageHero();
        List<Energising> energisingList = new ArrayList<>();
        energisingList.add(hero.getEnergising());
        energisingList.add(hero.getEnergising1());
        energisingList.add(hero.getEnergising2());


        hero.setNameHero(nameHero);
        hero.setTirHero(tirHero);
        hero.setClassHero(classHero);
        hero.setClassHero1(classHero1);
        hero.setClassHero2(classHero2);
        hero.setMax_xp(max_xp);
        hero.setMax_mp(max_mp);
        hero.setDamage(damage);
        hero.setSpeed_damage(speed_damage);
        hero.setRange_attack(range_attack);
        hero.setArmor(armor);
        hero.setMag_resist(mag_resist);
        hero.setName_skill(name_skill);
        hero.setActivation_skill(activation_skill);
        hero.setFull_text(full_text);
        hero.setDateTime(LocalDateTime.now());
        hero.setDetals_skill1(detals_skill1);
        hero.setDetals_skill2(detals_skill2);
        hero.setDetals_skill3(detals_skill3);
        hero.setDetals_skill4(detals_skill4);
        hero.setDetals_skill5(detals_skill5);
        hero.setDetals_skill6(detals_skill6);
        hero.setCast_mp(cast_mp);
        hero.setKd_skill(kd_skill);
        hero.setImageHero(imageHeroList);
        if(file1.getSize() > 0){
            saveImage(editToImageEntity(file1, hero.getImageHero().get(0).getId()));
            imageHeroList.set(0,editToImageEntity(file1, hero.getImageHero().get(0).getId()));

        }
        if(file2.getSize() > 0){
            saveImage(editToImageEntity(file2, hero.getImageHero().get(1).getId()));
            imageHeroList.set(1,editToImageEntity(file2, hero.getImageHero().get(1).getId()));
        }



            if (energisingList.size()>=1){hero.setEnergising(energisingList.get(0));}
            if (energisingList.size()>=2){hero.setEnergising1(energisingList.get(1));}
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
            if (sortTir.getTirHero().equals("ТИР 1") ||                                                                     //сортируем героев по тиру, ну в случаи есла админ указал не верный тир
                    sortTir.getTirHero().equals("ТИР 2") ||                                                                // то выводим это через 6 тир для админа, что бы была возможность исправить тир героя
                    sortTir.getTirHero().equals("ТИР 3") ||
                    sortTir.getTirHero().equals("ТИР 4") ||
                    sortTir.getTirHero().equals("ТИР 5")) {

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
