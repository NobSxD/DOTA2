package com.example.DOTA.services;

import com.example.DOTA.models.Hero;
import com.example.DOTA.models.image.ImageClassHero;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.repository.HeroRepository;
import com.example.DOTA.repository.image.ImageRepositoryClass;
import com.example.DOTA.repository.image.ImageRepositoryHero;
import com.example.DOTA.services.sort.SortHero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
@RequiredArgsConstructor

public class HeroService  {
    private final ImageRepositoryClass imageRepositoryClass;
    private final ImageRepositoryHero imageRepository;
    private final HeroRepository heroRepository;

   


    public List<Hero> listProducts() {
        return heroRepository.findAll();
    }

    public boolean heroExistById(Long id) {
        return heroRepository.existsById(id);
    }

    public List<ImageHero> imageHeroList(Hero hero) {
        return imageRepository.findByHero(hero);
    }

    public Hero getProductById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }

   public int summaHero(){
        return listProducts().size();
   }


    public SortHero heroDisplay2(Long id) {

        Hero hero = getProductById(id);
        List<ImageHero> imageHero = imageRepository.findByHero(hero);
        List<ImageClassHero> imgHeroList = imageRepositoryClass.findByName(hero.getClassHero());
        List<ImageClassHero> imgHeroList1 = imageRepositoryClass.findByName(hero.getClassHero1());
        List<ImageClassHero> imgHeroList2 = imageRepositoryClass.findByName(hero.getClassHero2());
        SortHero sortHero = new SortHero();
        sortHero.setHero(hero);
        if (imageHero.size() > 0) {
            sortHero.setIconHero(imageHero.get(0).getId());
        }

        if (imgHeroList.size() > 0) {
            sortHero.setIconClassHero(imgHeroList.get(0).getId());
        }
        if (imgHeroList1.size() > 0) {
            sortHero.setIconClassHero1(imgHeroList1.get(0).getId());
        }
        if (imgHeroList2.size() > 0) {
            sortHero.setIconClassHero2(imgHeroList2.get(0).getId());
        }

        return sortHero;
    }


    public void saveHero(Hero hero, MultipartFile file1) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntity(file1, hero);
        }

    }

    public void editHero(Hero hero, MultipartFile file1, SortHero sortHero) throws IOException {
        if (file1.getSize() != 0) {
            editToImageEntity(file1, hero, sortHero.getIconHero());
        }
        heroRepository.save(hero);
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


    private ImageHero editToImageEntity(MultipartFile file, Hero hero, Long id) throws IOException {
        ImageHero image = new ImageHero();
        image.setId(id);
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setHero(hero);

        return imageRepository.save(image);
    }

    public SortHero heroDisplay(Hero h) {

        Hero hero = h;
        List<ImageHero> imgHeroList = imageHeroList(hero);
        SortHero sortHero = new SortHero();
        for (ImageHero s : imgHeroList) {

            if (s.getName().equals("iconHero")) {
                sortHero.setIconHero(s.getId());
                sortHero.setHero(hero);
            }
        }
        return sortHero;
    }

    public List<SortHero> sortHeroes() {


        List<Hero> her = listProducts();

        List<SortHero> heroes = new ArrayList<>();

        for (Hero r : her) {
            List<ImageHero> imageHeroes = imageHeroList(r);
            SortHero sortHero = new SortHero();
            sortHero.setHero(r);
            List<ImageClassHero> imageRasHero = imageRepositoryClass.findByName(r.getClassHero());
            List<ImageClassHero> imageClassHero1 = imageRepositoryClass.findByName(r.getClassHero1());
            List<ImageClassHero> imageClassHero2 = imageRepositoryClass.findByName(r.getClassHero2());
            if (imageHeroes.size() > 0) {
                sortHero.setIconHero(imageHeroes.get(0).getId());
            }

            if (imageRasHero.size() > 0) {
                sortHero.setIconClassHero(imageRasHero.get(0).getId());
            }
            if (imageClassHero1.size() > 0) {
                sortHero.setIconClassHero1(imageClassHero1.get(0).getId());
            }
            if (imageClassHero2.size() > 0) {
                sortHero.setIconClassHero2(imageClassHero2.get(0).getId());
            }
            heroes.add(sortHero);
        }
        return heroes;
    }

    public void sortHeroList(List<SortHero> tir1, List<SortHero> tir2, List<SortHero> tir3, List<SortHero> tir4, List<SortHero> tir5, List<SortHero> tir6) {
        List<SortHero> sortHeroes = sortHeroes();
        for (SortHero sortTir : sortHeroes) {
            if (sortTir.getHero().getTirHero() == null) {
                tir4.add(sortTir);
            } else {
                if (sortTir.getHero().getTirHero().equals("ТИР 1") ||
                        sortTir.getHero().getTirHero().equals("ТИР 2") ||
                        sortTir.getHero().getTirHero().equals("ТИР 3") ||
                        sortTir.getHero().getTirHero().equals("ТИР 4") ||
                        sortTir.getHero().getTirHero().equals("ТИР 5")) {

                    if (sortTir.getHero().getTirHero().equals("ТИР 1")) {
                        tir1.add(sortTir);

                    }
                    if (sortTir.getHero().getTirHero().equals("ТИР 2")) {
                        tir2.add(sortTir);

                    }
                    if (sortTir.getHero().getTirHero().equals("ТИР 3")) {
                        tir3.add(sortTir);

                    }
                    if (sortTir.getHero().getTirHero().equals("ТИР 4")) {
                        tir4.add(sortTir);

                    }
                    if (sortTir.getHero().getTirHero().equals("ТИР 5")) {
                        tir5.add(sortTir);

                    }
                } else tir6.add(sortTir);

            }
        }

    }

    public void deleteHero(Long id) {
        Hero hero = getProductById(id);
        List<ImageHero> imageHeroList = imageHeroList(hero);

        for (ImageHero delete : imageHeroList
        ) {
            imageRepository.delete(delete);
        }

        heroRepository.delete(hero);

    }
}
