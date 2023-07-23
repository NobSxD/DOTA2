package com.example.DOTA.controller;

import com.example.DOTA.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PlayController {
    private final RasService rasService;
    private final ViewsService viewsService;
    private final HeroService heroService;
    private final EnergisingService energisingService;
    private final ClassHeroService classService;
    @GetMapping("/home/display/whatgame")
    public String play(){
        viewsService.viewsPlay();
        return "menu/button3/user/play";
    }

    @GetMapping("/home/build-creator")
    public String rasConstrutror(Model model) {
        model.addAttribute("ras", rasService.listRasHero());
        model.addAttribute("class", classService.listClassHero());
        model.addAttribute("en", energisingService.allEnergising());

        String tir1 = "ТИР 1";
        String tir2 = "ТИР 2";
        String tir3 = "ТИР 3";
        String tir4 = "ТИР 4";
        String tir5 = "ТИР 5";


        model.addAttribute("displayHeroTir1", heroService.displayHero(tir1));
        model.addAttribute("displayHeroTir2", heroService.displayHero(tir2));
        model.addAttribute("displayHeroTir3", heroService.displayHero(tir3));
        model.addAttribute("displayHeroTir4", heroService.displayHero(tir4));
        model.addAttribute("displayHeroTir5", heroService.displayHero(tir5));

      return  "menu/button3/user/constryctor";
    }
    @GetMapping("/home/income-gold")
    public String incom(){
        return "menu/button3/user/income";
    }

    @GetMapping("/home/mana")
    public String mana(){
        return "menu/button3/user/mana";
    }

    @GetMapping("/home/ranks")
    public String ranks(){
        return "menu/button3/user/ranks";
    }
    @GetMapping("/home/courier-experience")
    public String courierExperience(){
        return "menu/button3/user/courierExperience";
    }


}
