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
    public boolean heroExistById(Long id) {
        return heroRepository.existsById(id);
    }
    public List<ImageHero> imageHeroList(Hero hero){
        return imageRepository.findByHero(hero);
    }
    public Hero getProductById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }


    public SortHero heroDisplay(Hero h){

        Hero hero = h;
        List<ImageHero> imgHeroList = imageHeroList(hero);
            SortHero sortHero = new SortHero();
            for (ImageHero s : imgHeroList) {

                if (s.getName().equals("iconHero")) {
                    sortHero.setIconHero(s.getId());
                    sortHero.setHero(hero);
                }
                if (s.getName().equals("iconClassHero")) {
                    sortHero.setIconClassHero(s.getId());
                    sortHero.setHero(hero);
                }
                if (s.getName().equals("iconClassHero1")) {
                    sortHero.setIconClassHero1(s.getId());
                    sortHero.setHero(s.getHero());
                    sortHero.setHero(hero);
                }
                if (s.getName().equals("iconClassHero2")) {
                    sortHero.setIconClassHero1(s.getId());
                    sortHero.setHero(hero);
                }
            }
        return sortHero;
    }

    public SortHero heroDisplay2(Long id){

        Hero hero = getProductById(id);
        List<ImageHero> imgHeroList = imageHeroList(hero);
        SortHero sortHero = new SortHero();
        for (ImageHero s : imgHeroList) {

            if (s.getName().equals("iconHero")) {
                sortHero.setIconHero(s.getId());
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
            if (s.getName().equals("iconClassHero2")) {
                sortHero.setIconClassHero1(s.getId());
                sortHero.setHero(s.getHero());
            }
        }
        return sortHero;
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

    public void editHero(Hero hero, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4,
                         SortHero sortHero) throws IOException {
        if (file1.getSize() != 0) {
            editToImageEntity(file1, hero, sortHero.getIconHero());
        }
        if (file2.getSize() != 0) {
            editToImageEntity(file2,hero,sortHero.getIconClassHero());
        }
        if (file3.getSize() != 0) {
            editToImageEntity(file3,hero,sortHero.getIconClassHero1());
        }
        if (file4.getSize() != 0) {
            editToImageEntity(file4,hero,sortHero.getIconClassHero2());
        }
        heroRepository.save(hero);
    }

    private ImageHero editToImageEntity(MultipartFile file, Hero hero,Long id) throws IOException {
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

    public List<SortHero> sortHeroes(){

        List<Hero> her = listProducts();

        List<SortHero> heroes = new ArrayList<>();

        for (Hero r : her) {
        List<ImageHero> imgHeroList = imageHeroList(r);
        SortHero sortHero = new SortHero();
        sortHero.setHero(r);
        for (ImageHero s : imgHeroList) {

            if (s.getName().equals("iconHero")) {
                sortHero.setIconHero(s.getId());

            }

            if (s.getName().equals("iconClassHero")) {
                sortHero.setIconClassHero(s.getId());

            }
            if (s.getName().equals("iconClassHero1")) {
                sortHero.setIconClassHero1(s.getId());

            }
            if (s.getName().equals("iconClassHero2")) {
                sortHero.setIconClassHero2(s.getId());

            }
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

    public void deleteHero(Long id){
        Hero hero = getProductById(id);
        List<ImageHero> imageHeroList = imageHeroList(hero);

        for (ImageHero delete: imageHeroList
             ) {
            imageRepository.delete(delete);
        }

        heroRepository.delete(hero);

    }
}
