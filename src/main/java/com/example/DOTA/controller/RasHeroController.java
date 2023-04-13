package com.example.DOTA.controller;

import com.example.DOTA.services.RasHeroService;
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
public class RasHeroController {
    private final RasHeroService rasHeroService;

    @GetMapping("/admin/add/ras")
    public String add(){
        return "menu/button2/admin/ras/rasAdd";
    }

    @PostMapping("/admin/add/ras")
    public String save(Model model,
                       @RequestParam String classHero,
                       @RequestParam String full_text,
                       @RequestParam("iconClassHero") MultipartFile file1) throws IOException {


        rasHeroService.saveRasHero(file1,classHero,full_text);
        return "redirect:/admin/add/ras";
    }


    @GetMapping("/admin/display/ras")
    public String display(Model model){
        model.addAttribute("ras" ,rasHeroService.listRasHero());
        return "menu/button2/admin/ras/rasDisplay";
    }

    @GetMapping("/admin/detals/ras/{id}")
    public String detalsRas(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("ras", rasHeroService.rasHero(id));
        return "menu/button2/admin/ras/rasDetals";
    }



    @GetMapping("/admin/delete/ras/{id}")
    public String deleteRas(@PathVariable(value = "id")Long id){
        rasHeroService.delete(rasHeroService.rasHero(id));
        return "redirect:/admin/display/ras";
    }
    @GetMapping("/admin/edit/ras/{id}")
    public String editRas(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("ras",rasHeroService.rasHero(id));
        return "menu/button2/admin/ras/rasEdit";
    }
    @PostMapping("/admin/edit/ras/{id}")
    public String update(@PathVariable(value = "id")Long id, @RequestParam String className,@RequestParam String detals, @RequestParam("iconClassHero") MultipartFile multipartFile) throws IOException {
        rasHeroService.updateRasHero(multipartFile,className,detals,id);
        return "redirect:/admin/display/ras";
    }





    @GetMapping("/home/display/ras")
    public String displayUser(Model model){
        model.addAttribute("ras" ,rasHeroService.listRasHero());
        return "menu/button2/user/ras/rasDisplay";
    }

    @GetMapping("/home/detals/ras/{id}")
    public String detalsUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("id", rasHeroService.rasHero(id));
        return "menu/button2/user/ras/rasDetals";
    }
}
