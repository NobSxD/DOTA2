package com.example.DOTA.controller;

import com.example.DOTA.models.ClassHero;
import com.example.DOTA.repository.ClassRepository;
import com.example.DOTA.services.ClassHeroService;
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
public class ClassHeroController {
    private final ClassRepository classRepository;
    private final ClassHeroService classHeroService;

    @GetMapping("/admin/class")
    public String hero(Model model){

        return "ClassHeroAdd";
    }

    @PostMapping("/admin/class")
    public String save(Model model,
                       @RequestParam String classHero,
                       @RequestParam String full_text,
                       @RequestParam("iconClassHero") MultipartFile file1) throws IOException {
        ClassHero classHeroNew = new ClassHero(classHero,full_text);
        classRepository.save(classHeroNew);

        classHeroService.saveClassHero(classHeroNew,file1);
        return "redirect:/add/class";
    }
}
