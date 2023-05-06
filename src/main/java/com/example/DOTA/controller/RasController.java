package com.example.DOTA.controller;

import com.example.DOTA.services.EnergisingService;
import com.example.DOTA.services.RasService;
import com.example.DOTA.services.ViewsService;
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
public class RasController {
    private final RasService rasHeroService;
    private final EnergisingService energisingService;
    private final ViewsService viewsService;

    @GetMapping("/admin/add/ras")
    private String add(Model model){
        model.addAttribute("rassa", energisingService.allEnergising());
        return "menu/button2/admin/ras/rasAdd";
    }

    @PostMapping("/admin/add/ras")
    private String save(@RequestParam String name) throws IOException {
        rasHeroService.saveRasHero(name);
        return "redirect:/admin/add/ras";
    }


    @GetMapping("/admin/display/ras")
    private String display(Model model){
        model.addAttribute("ras" ,rasHeroService.listRasHero());
        return "menu/button2/admin/ras/rasDisplay";
    }

    @GetMapping("/admin/detals/ras/{id}")
    private String detalsRas(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("ras", rasHeroService.rasHero(id));
        return "menu/button2/admin/ras/rasDetals";
    }



    @GetMapping("/admin/delete/ras/{id}")
    private String deleteRas(@PathVariable(value = "id")Long id){
        rasHeroService.delete(rasHeroService.rasHero(id));
        return "redirect:/admin/display/ras";
    }
    @GetMapping("/admin/edit/ras/{id}")
    private String editRas(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("ras",rasHeroService.rasHero(id));
        return "menu/button2/admin/ras/rasEdit";
    }
    @PostMapping("/admin/edit/ras/{id}")
    private String update(@PathVariable(value = "id")Long id, @RequestParam String name) throws IOException {
        rasHeroService.updateRasHero(name,id);
        return "redirect:/admin/display/ras";
    }





    @GetMapping("/home/display/ras")
    private String displayUser(Model model){
        viewsService.viewsRas();
        model.addAttribute("ras" ,rasHeroService.listRasHero());
        return "menu/button2/user/ras/rasDisplay";
    }

    @GetMapping("/home/detals/ras/{id}")
    private String detalsUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("id", rasHeroService.rasHero(id));
        return "menu/button2/user/ras/rasDetals";
    }
}
