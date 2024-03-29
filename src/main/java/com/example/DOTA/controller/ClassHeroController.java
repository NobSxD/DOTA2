package com.example.DOTA.controller;

import com.example.DOTA.services.ClassHeroService;
import com.example.DOTA.services.EnergisingService;
import com.example.DOTA.services.ViewsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ClassHeroController {


    private final ClassHeroService classHeroService;
    private final ViewsService viewsService;

    @GetMapping("/admin/add/class")
    public String add(){
        return "menu/button2/admin/class/classAdd";
    }

    @PostMapping("/admin/add/class")
    public String add(@RequestParam String classHero) throws IOException {
        classHeroService.saveClassHero(classHero);
        return "redirect:/admin/add/class";
    }


    @GetMapping("/admin/display/class")
    public String display(Model model){
        model.addAttribute("class" ,classHeroService.listClassHero());
        return "menu/button2/admin/class/classDisplay";
    }

    @GetMapping("/admin/detals/class/{id}")
    public String detailsClassAdmin(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("class", classHeroService.classHero(id));
        return "menu/button2/admin/class/classDetals";
    }



    @GetMapping("/admin/delete/class/{id}")
    public String deleteClass(@PathVariable(value = "id")Long id){
        classHeroService.deleteClass(classHeroService.classHero(id));
        return "redirect:/admin/display/class";
    }
    @GetMapping("/admin/edit/class/{id}")
    public String editClass(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("class",classHeroService.classHero(id));
        return "menu/button2/admin/class/classEdit";
    }
    @PostMapping("/admin/edit/class/{id}")
    public String update(@PathVariable(value = "id")Long id, @RequestParam String classHero) throws IOException {
        classHeroService.updateClassHero(classHero,id);
        return "redirect:/admin/display/class";
    }





    @GetMapping("/home/display/class")
    private String displayClass(Model model){
        viewsService.viewsClass();
        model.addAttribute("class" ,classHeroService.listClassHero());
        return "menu/button2/user/class/classDisplay";
    }

    @GetMapping("/home/detals/class/{id}")
    private String detailsClass(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("id", classHeroService.classHero(id));
        return "menu/button2/user/class/classDetals";
    }
}
