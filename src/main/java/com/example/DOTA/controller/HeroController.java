package com.example.DOTA.controller;

import com.example.DOTA.models.Hero;
import com.example.DOTA.services.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;




    @PostMapping("/admin/hero")
    public String save(Model model,
                       @RequestParam String hero,
                       @RequestParam String tear,
                       @RequestParam String classHero,
                       @RequestParam String classHero1,
                       @RequestParam String classHero2,
                       @RequestParam String classHero3,
                       @RequestParam String full_text,
                       @RequestParam("iconHero") MultipartFile file1,
                       @RequestParam("iconSpecies") MultipartFile file2,
                       @RequestParam("iconClassHero") MultipartFile file3,
                       @RequestParam("iconClassHero1") MultipartFile file4) throws IOException {
        Hero heroNew = new Hero(hero, tear, classHero,classHero1, classHero2,classHero3, full_text);
        heroService.saveHero(heroNew, file1, file2, file3, file4);
        return "redirect:/add/hero";
    }

    @GetMapping("/home/hero/{id}")
    public String getHero(Model model, @PathVariable Long id) {
        Hero hero = heroService.getProductById(id);
        model.addAttribute("hero", hero);
        return "userMenuTop/button2/hero";
    }

    @GetMapping("/home/hero")
    public String heroGet(Model model) {

        model.addAttribute("imageHero", heroService.sortHeroes());
        return "userMenuTop/button2/hero";
    }
    @GetMapping("/admin/hero")
    public String heroAdmin(Model model) {

        model.addAttribute("editHero", heroService.sortHeroes());
        return "userMenuTop/button2/admin/displayHero";
    }


    @GetMapping("/admin/{id}")
    public String HeroDetals(@PathVariable(value = "id") long id, Model model){
        if (!heroService.heroExistById(id)){
            return "redirect:/admin/hero";
        }
        model.addAttribute("editHero", heroService.heroDisplay(id));
        return "userMenuTop/button2/admin/edit/editHero";
    }


}
