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
        return "menu/button2/admin/classHero/classAdd";
    }

    @PostMapping("/admin/class/add")
    public String save(Model model,
                       @RequestParam String classHero,
                       @RequestParam String full_text,
                       @RequestParam("iconClassHero") MultipartFile file1) throws IOException {


        classHeroService.saveClassHero(file1,classHero,full_text);
        return "redirect:/admin/class/add";
    }


    @GetMapping("/admin/display/class")
    public String display(Model model){
        model.addAttribute("class" ,classHeroService.listClassHero());
        return "menu/button2/admin/classHero/classDisplay";
    }

    @GetMapping("/admin/detals/class/{id}")
    public String detals1(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("id", classHeroService.classHero(id));
        return "menu/button2/admin/classHero/classDetals";
    }



    @GetMapping("/admin/delete/class/{id}")
    public String delete(@PathVariable(value = "id")Long id){
        classHeroService.delete(classHeroService.classHero(id));
        return "redirect:/admin/display/class";
    }





    @GetMapping("/home/display/class")
    public String displayUser(Model model){
        model.addAttribute("class" ,classHeroService.listClassHero());
        return "menu/button2/user/classHero/classDisplay";
    }

    @GetMapping("/home/detals/class/{id}")
    public String detalsUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("id", classHeroService.classHero(id));
        return "menu/button2/user/classHero/classDetals";
    }
}
