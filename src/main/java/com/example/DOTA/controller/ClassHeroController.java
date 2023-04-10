package com.example.DOTA.controller;

import com.example.DOTA.services.ClassHeroService;
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
public class ClassHeroController {

    private final ClassHeroService classHeroService;

    @GetMapping("/admin/class/add")
    public String add(){
        return "/userMenuTop/button2/admin/add/ClassHeroAdd";
    }

    @PostMapping("/admin/class/add")
    public String save(Model model,
                       @RequestParam String classHero,
                       @RequestParam String full_text,
                       @RequestParam("iconClassHero") MultipartFile file1) throws IOException {


        classHeroService.saveClassHero(file1,classHero,full_text);
        return "redirect:/admin/class/add";
    }



    @GetMapping("/admin/class/edit")
    public String adminClass(Model model){
        model.addAttribute("class" ,classHeroService.listClassHero());
        return "/userMenuTop/button2/admin/edit/classDisplay";
    }
    @GetMapping("/admin/class/delete/{id}")
    public String delete(@PathVariable(value = "id")Long id){
        classHeroService.delete(classHeroService.classHero(id));
        return "redirect:/admin/class";
    }

    @GetMapping("/admin/class/{id}")
    public String idClassHeroAdmin(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("id", classHeroService.classHero(id));
        return "userMenuTop/button2/classDetals";
    }



    @GetMapping("/home/class")
    public String classHero(Model model){
        model.addAttribute("class" ,classHeroService.listClassHero());
        return "userMenuTop/button2/displayClassHero";
    }

    @GetMapping("/home/class/{id}")
    public String idClassHero(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("id", classHeroService.classHero(id));
        return "userMenuTop/button2/classDetals";
    }
}
