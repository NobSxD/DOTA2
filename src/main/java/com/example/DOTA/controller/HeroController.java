package com.example.DOTA.controller;

import com.example.DOTA.models.Hero;
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
    public String heroAdd(Model model) {
        return "userMenuTop/button2/admin/add/heroAdd";
    }

    @PostMapping("/admin/hero")
    public String saveHero(Model model,
                           @RequestParam String hero,
                           @RequestParam String tear,
                           @RequestParam String classHero,
                           @RequestParam String classHero1,
                           @RequestParam String classHero2,
                           @RequestParam String full_text,
                           @RequestParam("iconHero") MultipartFile file1,
                           @RequestParam("iconClassHero") MultipartFile file2,
                           @RequestParam("iconClassHero1") MultipartFile file3,
                           @RequestParam("iconClassHero2") MultipartFile file4) throws IOException {
        Hero heroNew = new Hero(hero, tear, classHero, classHero1, classHero2, full_text);
        heroService.saveHero(heroNew, file1, file2, file3, file4);
        return "redirect:/admin/hero";
    }

    @GetMapping("/admin/{id}")
    public String detals(Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("editHero", heroService.heroDisplay2(id));
        return "userMenuTop/button2/admin/edit/editHero";
    }

    @PostMapping("/admin/{id}")
    public String editHero(@PathVariable(value = "id") long id,
                           @RequestParam String hero,
                           @RequestParam String tear,
                           @RequestParam String classHero,
                           @RequestParam String classHero1,
                           @RequestParam String classHero2,
                           @RequestParam String full_text,
                           @RequestParam("iconHero") MultipartFile file1,
                           @RequestParam("iconClassHero") MultipartFile file2,
                           @RequestParam("iconClassHero1") MultipartFile file3,
                           @RequestParam("iconClassHero2") MultipartFile file4) throws IOException {
        if (!heroService.heroExistById(id)) {
            return "redirect:/admin/hero";
        }
        Hero heroHero = new Hero(hero, tear, classHero, classHero1, classHero2, full_text);
        heroHero.setId(id);
        heroService.editHero(heroHero, file1, file2, file3, file4, heroService.heroDisplay(heroHero));

        return "redirect:/admin/hero/edit";
    }

    @GetMapping("/admin/hero/edit")
    public String displayHeroAdmin(Model model) {
        List<SortHero> tir1 = new ArrayList<>();
        List<SortHero> tir2 = new ArrayList<>();
        List<SortHero> tir3 = new ArrayList<>();
        List<SortHero> tir4 = new ArrayList<>();
        List<SortHero> tir5 = new ArrayList<>();
        List<SortHero> tir6 = new ArrayList<>();

        heroService.sortHeroList(tir1,tir2,tir3,tir4,tir5,tir6);


        model.addAttribute("displayHeroTir1", tir1);
        model.addAttribute("displayHeroTir2", tir2);
        model.addAttribute("displayHeroTir3", tir3);
        model.addAttribute("displayHeroTir4", tir4);
        model.addAttribute("displayHeroTir5", tir5);
        model.addAttribute("displayHeroTir6", tir6);


        return "userMenuTop/button2/admin/displayHero";
    }

    @GetMapping("/admin/hero/delete/{id}")
    public String delete(@PathVariable(value = "id")Long id){
        heroService.deleteHero(id);
        return "redirect:/admin/hero/edit";
    }


    @GetMapping("/home/hero/{id}")
    public String heroDetailsHom(Model model, @PathVariable Long id) {

        return "userMenuTop/button2/hero";
    }

    @GetMapping("/home/hero")
    public String displayHeroUser(Model model) {
        List<SortHero> tir1 = new ArrayList<>();
        List<SortHero> tir2 = new ArrayList<>();
        List<SortHero> tir3 = new ArrayList<>();
        List<SortHero> tir4 = new ArrayList<>();
        List<SortHero> tir5 = new ArrayList<>();
        List<SortHero> tir6 = new ArrayList<>();

        heroService.sortHeroList(tir1,tir2,tir3,tir4,tir5,tir6);


        model.addAttribute("displayHeroTir1", tir1);
        model.addAttribute("displayHeroTir2", tir2);
        model.addAttribute("displayHeroTir3", tir3);
        model.addAttribute("displayHeroTir4", tir4);
        model.addAttribute("displayHeroTir5", tir5);


        model.addAttribute("imageHero", heroService.sortHeroes());
        return "userMenuTop/button2/hero";
    }



}
