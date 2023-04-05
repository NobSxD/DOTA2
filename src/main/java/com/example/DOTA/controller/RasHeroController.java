package com.example.DOTA.controller;

import com.example.DOTA.models.RasHero;
import com.example.DOTA.repository.RasRepository;
import com.example.DOTA.services.RasHeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
@RequiredArgsConstructor
public class RasHeroController {
    private final RasRepository repository;
    private final RasHeroService rasHeroService;

    @GetMapping("/admin/ras")
    public String hero(Model model){

        return  "userMenuTop/button2/admin/add/RasHero";
    }

    @PostMapping("/admin/ras")
    public String save(Model model,
                       @RequestParam String rasHero,
                       @RequestParam String full_text,
                       @RequestParam("iconRasHero") MultipartFile file1) throws IOException {
        RasHero rasHeroNew = new RasHero(rasHero,full_text);
        repository.save(rasHeroNew);

        rasHeroService.saveClassHero(rasHeroNew,file1);
        return "redirect:/admin/class";
    }
}
