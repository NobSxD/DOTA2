package com.example.DOTA.controller;

import com.example.DOTA.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final HeroService heroService;
    private final ClassHeroService classHeroService;
    private final RasService rasHeroService;
    private final SkillService skillService;

    private final WeaponService weaponService;

    private final GuideService guideService;


    @GetMapping("/admin")
    public String adminHome(Model model){
        model.addAttribute("sumSkill", skillService.summaSkill());
        model.addAttribute("sumRas", rasHeroService.summaRas());
        model.addAttribute("sumHero",heroService.summaHero());
        model.addAttribute("sumClass",classHeroService.summaClass());
        model.addAttribute("sumItems", weaponService.summaItems());
        model.addAttribute("guide", guideService.countGuide());
        return "menu/button1/autoChessAdmin";
    }


}
