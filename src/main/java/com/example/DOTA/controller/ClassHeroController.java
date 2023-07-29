package com.example.DOTA.controller;

import com.example.DOTA.services.ClassHeroService;
import com.example.DOTA.services.EnergisingService;
import com.example.DOTA.services.ViewsService;
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

    private final EnergisingService energisingService;
    private final ViewsService viewsService;

    @GetMapping("/admin/add/class")
    private String add(Model model){
        model.addAttribute("class", energisingService.allEnergising());
        return "menu/button2/admin/class/classAdd";
    }

    @PostMapping("/admin/add/class")
    private String save(@RequestParam String classHero) throws IOException {
        classHeroService.saveClassHero(classHero);
        return "redirect:/admin/add/class";
    }


    @GetMapping("/admin/display/class")
    private String display(Model model){
        model.addAttribute("class" ,classHeroService.listClassHero());
        return "menu/button2/admin/class/classDisplay";
    }

    @GetMapping("/admin/detals/class/{id}")
    private String detalsRas(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("class", classHeroService.classHero(id));
        return "menu/button2/admin/class/classDetals";
    }



    @GetMapping("/admin/delete/class/{id}")
    private String deleteClass(@PathVariable(value = "id")Long id){
        classHeroService.deleteClass(classHeroService.classHero(id));
        return "redirect:/admin/display/class";
    }
    @GetMapping("/admin/edit/class/{id}")
    private String editRas(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("class",classHeroService.classHero(id));
        return "menu/button2/admin/class/classEdit";
    }
    @PostMapping("/admin/edit/class/{id}")
    private String update(@PathVariable(value = "id")Long id, @RequestParam String classHero) throws IOException {
        classHeroService.updateClassHero(classHero,id);
        return "redirect:/admin/display/class";
    }





    @GetMapping("/home/display/class")
    private String displayUser(Model model){
        viewsService.viewsClass();
        model.addAttribute("class" ,classHeroService.listClassHero());
        return "menu/button2/user/class/classDisplay";
    }

    @GetMapping("/home/detals/class/{id}")
    private String detalsUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("id", classHeroService.classHero(id));
        return "menu/button2/user/class/classDetals";
    }
}
