package com.example.DOTA.controller;

import com.example.DOTA.models.Hero;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.services.HeroService;
import com.example.DOTA.services.sort.SortHero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;


    @GetMapping("/admin/hero")
    public String hero(Model model) {

        return "heroAdd";
    }

    @PostMapping("/add/hero")
    public String save(Model model,
                       @RequestParam String hero,
                       @RequestParam String tear,
                       @RequestParam String classHero,
                       @RequestParam String classHero2,
                       @RequestParam String classHero3,
                       @RequestParam String full_text,
                       @RequestParam("iconHero") MultipartFile file1,
                       @RequestParam("iconSpecies") MultipartFile file2,
                       @RequestParam("iconClassHero") MultipartFile file3,
                       @RequestParam("iconClassHero1") MultipartFile file4) throws IOException {
        Hero heroNew = new Hero(hero, tear, classHero, classHero2,classHero3, full_text);
        heroService.saveHero(heroNew, file1, file2, file3, file4);
        return "redirect:/add/hero";
    }

    @GetMapping("/home/hero/{id}")
    public String getHero(Model model, @PathVariable Long id) {
        Hero hero = heroService.getProductById(id);
        model.addAttribute("hero", hero);
        return "userobject/button2/hero";
    }

    @GetMapping("/home/hero")
    public String heroGet(Model model) {


        model.addAttribute("imageHero", heroService.sortHeroes());
        return "userobject/button2/hero";
    }

    @GetMapping("/admin/{id}")
    public String editHero(){
        return "adminCreate/editHero";
    }


}
