package com.example.DOTA.controller;

import com.example.DOTA.models.Hero;
import com.example.DOTA.services.ClassHeroService;
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
    private final ClassHeroService imageClassHero;


    @GetMapping("/admin/add/hero")
    public String heroAdd(Model model) {
        model.addAttribute("class", imageClassHero.findAll());
        return "menu/button2/admin/hero/heroAdd";
    }

    @PostMapping("/admin/add/hero")
    public String saveHero(Model model,
                           @RequestParam String hero,
                           @RequestParam String tear,
                           @RequestParam String classHero,
                           @RequestParam String classHero1,
                           @RequestParam String classHero2,
                           @RequestParam String full_text,
                           @RequestParam("iconHero") MultipartFile file1
    )throws IOException {
        Hero heroNew = new Hero(hero, tear, classHero, classHero1, classHero2, full_text);
        heroService.saveHero(heroNew, file1);
        return "redirect:/admin/add/hero";
    }


    @GetMapping("/admin/display/hero")
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


        return "menu/button2/admin/hero/heroDisplay";
    }

    @GetMapping("/admin/detals/hero/{id}")
    public String detalsAdmin(Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("detals", heroService.heroDisplay2(id));
        return "menu/button2/admin/hero/heroDetals";
    }

    @GetMapping("/admin/edit/hero/{id}")
    public String editIdHero(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("class", imageClassHero.findAll());
        model.addAttribute("detals", heroService.heroDisplay2(id));
        return "menu/button2/admin/hero/heroEdit";
    }

    @PostMapping("/admin/edit/hero/{id}")
    public String editHero(@PathVariable(value = "id") long id,
                           @RequestParam String hero,
                           @RequestParam String tear,
                           @RequestParam String classHero,
                           @RequestParam String classHero1,
                           @RequestParam String classHero2,
                           @RequestParam String full_text,
                           @RequestParam("iconHero") MultipartFile file1) throws IOException {
        if (!heroService.heroExistById(id)) {
            return "redirect:/admin/display/hero";
        }
        Hero heroHero = new Hero(hero, tear, classHero, classHero1, classHero2, full_text);
        heroHero.setId(id);
        heroService.editHero(heroHero, file1,heroService.heroDisplay(heroHero));

        return "redirect:/admin/display/hero";
    }



    @GetMapping("/admin/delete/hero/{id}")
    public String delete(@PathVariable(value = "id")Long id){
        heroService.deleteHero(id);
        return "redirect:/admin/display/hero";
    }


    @GetMapping("/home/display/hero")
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
        return "menu/button2/user/hero/heroDisplay";
    }

    @GetMapping("/home/display/hero/{id}")
    public String heroDetailsHom(Model model, @PathVariable Long id) {
        model.addAttribute("detals",heroService.heroDisplay2(id));

        return "menu/button2/user/hero/heroDetals";
    }



}
