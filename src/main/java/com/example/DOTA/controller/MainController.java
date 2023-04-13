package com.example.DOTA.controller;

import com.example.DOTA.services.ClassHeroService;
import com.example.DOTA.services.HeroService;
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
    @GetMapping("/")
    public String main(Model model) throws SQLException, IOException {
        model.addAttribute("sumHero",heroService.summaHero());
        model.addAttribute("sumClass",classHeroService.summaClass());
        return "menu/button1/autoChess";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("sumHero",heroService.summaHero());
        model.addAttribute("sumClass",classHeroService.summaClass());
        return "menu/button1/autoChess";
    }

    @GetMapping("/admin")
    public String adminHome(Model model){
        model.addAttribute("sumHero",heroService.summaHero());
        model.addAttribute("sumClass",classHeroService.summaClass());
        return "menu/button1/autoChessAdmin";
    }


}
