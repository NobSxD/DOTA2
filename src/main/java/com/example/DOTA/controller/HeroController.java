package com.example.DOTA.controller;

import com.example.DOTA.models.Hero;
import com.example.DOTA.repository.ViewsRepository;
import com.example.DOTA.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
    private final EnergisingService energisingService;
    private final ViewsService viewsService;



    @GetMapping("/admin/add/hero")
    private String heroAdd(Model model) {
        model.addAttribute("ras", energisingService.allEnergising());

        return "menu/button2/admin/hero/heroAdd";
    }

@PostMapping("/admin/add/hero")
private String saveHero(Hero hero, @RequestParam("iconHero") MultipartFile file1,
                        @RequestParam("iconHero1") MultipartFile file2)throws IOException {
    heroService.saveHero(hero,file1,file2);
    return "redirect:/admin/add/hero";
}


    @GetMapping("/admin/display/hero")
    private String displayHeroAdmin(Model model) {
        String tir1 = "ТИР 1";
        String tir2 = "ТИР 2";
        String tir3 = "ТИР 3";
        String tir4 = "ТИР 4";
        String tir5 = "ТИР 5";
        String tir6 = "ТИР 6";


        model.addAttribute("displayHeroTir1", heroService.displayHero(tir1));
        model.addAttribute("displayHeroTir2", heroService.displayHero(tir2));
        model.addAttribute("displayHeroTir3", heroService.displayHero(tir3));
        model.addAttribute("displayHeroTir4", heroService.displayHero(tir4));
        model.addAttribute("displayHeroTir5", heroService.displayHero(tir5));
        model.addAttribute("displayHeroTir6", heroService.displayHero(tir6));


        return "menu/button2/admin/hero/heroDisplay";
    }

    @GetMapping("/admin/detals/hero/{id}")
    private String detalsAdmin(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("detals", heroService.getHeroById(id));
        return "menu/button2/admin/hero/heroDetals";
    }

    @GetMapping("/admin/edit/hero/{id}")
    private String editIdHero(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("ras", energisingService.allEnergising());
        model.addAttribute("detals", heroService.getHeroById(id));
        return "menu/button2/admin/hero/heroEdit";
    }

    @PostMapping("/admin/edit/hero/{id}")
    private String editHero(Hero hero,
                            @RequestParam("iconHero") MultipartFile file1,
                            @RequestParam("iconHero1") MultipartFile file2) throws IOException {
        if (!heroService.heroExistById(hero.getId())) {
            return "redirect:/admin/display/hero";
        }

        heroService.editHero(hero,file1,file2);

        return "redirect:/admin/display/hero";
    }



    @GetMapping("/admin/delete/hero/{id}")
    private String delete(@PathVariable(value = "id")Long id){
        heroService.deleteHero(id);
        return "redirect:/admin/display/hero";
    }







    @GetMapping("/home/display/hero")
    private String displayHeroUser(Model model) {

        model.addAttribute("displayHeroTir1", heroService.displayHero("ТИР 1"));
        model.addAttribute("displayHeroTir2", heroService.displayHero("ТИР 2"));
        model.addAttribute("displayHeroTir3", heroService.displayHero("ТИР 3"));
        model.addAttribute("displayHeroTir4", heroService.displayHero("ТИР 4"));
        model.addAttribute("displayHeroTir5", heroService.displayHero("ТИР 5"));




        return "menu/button2/user/hero/heroDisplay";
    }




    @GetMapping("/home/detals/hero/{id}")
    private String detalsHome(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("detals", heroService.getHeroById(id));
        return "menu/button2/user/hero/heroDetals";
    }



}
