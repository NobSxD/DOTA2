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

    @GetMapping("/admin/add/class")
    public String add(){
        return "menu/button2/admin/classHero/classAdd";
    }

    @PostMapping("/admin/add/class")
    public String save(Model model,
                       @RequestParam String classHero,
                       @RequestParam String full_text,
                       @RequestParam("iconClassHero") MultipartFile file1) throws IOException {


        classHeroService.saveClassHero(file1,classHero,full_text);
        return "redirect:/admin/add/class";
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
    @GetMapping("/admin/edit/class/{id}")
    public String edit(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("class",classHeroService.classHero(id));
        return "menu/button2/admin/classHero/classEdit";
    }
    @PostMapping("/admin/edit/class/{id}")
    public String update(@PathVariable(value = "id")Long id, @RequestParam String className,@RequestParam String detals, @RequestParam("iconClassHero") MultipartFile multipartFile) throws IOException {
        classHeroService.updateClassHero(multipartFile,className,detals,id);
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
