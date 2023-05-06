package com.example.DOTA.controller;

import com.example.DOTA.models.Hero;
import com.example.DOTA.repository.ViewsRepository;
import com.example.DOTA.services.ClassHeroService;
import com.example.DOTA.services.HeroService;
import com.example.DOTA.services.RasService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;
    private final RasService rasService;
    private final ClassHeroService classHeroService;
    private final ViewsService viewsService;
    private final ViewsRepository viewsRepository;


    @GetMapping("/admin/add/hero")
    private String heroAdd(Model model) {
        model.addAttribute("ras", rasService.listRasHero());
        model.addAttribute("class", classHeroService.listClassHero());
        return "menu/button2/admin/hero/heroAdd";
    }

    @PostMapping("/admin/add/hero")
    private String saveHero(Model model,
                           @RequestParam String hero,
                           @RequestParam String tear,
                           @RequestParam String classHero,
                           @RequestParam String classHero1,
                           @RequestParam String classHero2,
                           @RequestParam String full_text,
                           @RequestParam("iconHero") MultipartFile file1
    )throws IOException {
        heroService.saveHero(hero,tear,classHero,classHero1,classHero2,full_text, file1);
        return "redirect:/admin/add/hero";
    }


    @GetMapping("/admin/display/hero")
    private String displayHeroAdmin(Model model) {
        List<Hero> tir1 = new ArrayList<>();
        List<Hero> tir2 = new ArrayList<>();
        List<Hero> tir3 = new ArrayList<>();
        List<Hero> tir4 = new ArrayList<>();
        List<Hero> tir5 = new ArrayList<>();
        List<Hero> tir6 = new ArrayList<>();

        heroService.displayHero(tir1,tir2,tir3,tir4,tir5,tir6);


        model.addAttribute("displayHeroTir1", tir1);
        model.addAttribute("displayHeroTir2", tir2);
        model.addAttribute("displayHeroTir3", tir3);
        model.addAttribute("displayHeroTir4", tir4);
        model.addAttribute("displayHeroTir5", tir5);
        model.addAttribute("displayHeroTir6", tir6);


        return "menu/button2/admin/hero/heroDisplay";
    }

    @GetMapping("/admin/detals/hero/{id}")
    private String detalsAdmin(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("detals", heroService.getHeroById(id));
        return "menu/button2/admin/hero/heroDetals";
    }

    @GetMapping("/admin/edit/hero/{id}")
    private String editIdHero(@PathVariable(value = "id") Long id, Model model){

        model.addAttribute("detals", heroService.getHeroById(id));
        return "menu/button2/admin/hero/heroEdit";
    }

    @PostMapping("/admin/edit/hero/{id}")
    private String editHero(@PathVariable(value = "id") Long id,
                           @RequestParam String hero,
                           @RequestParam String tear,
                           @RequestParam String classHero,
                           @RequestParam String classHero1,
                           @RequestParam String classHero2,
                           @RequestParam String full_text,
                           @RequestParam("iconHero") MultipartFile file1) throws IOException {
        if (!heroService.heroExistById(id)) {
            return "redirect:/admin/display/hero";
        }

        heroService.editHero(id,hero,tear,classHero,classHero1,classHero2,full_text,file1);

        return "redirect:/admin/display/hero";
    }



    @GetMapping("/admin/delete/hero/{id}")
    private String delete(@PathVariable(value = "id")Long id){
        heroService.deleteHero(id);
        return "redirect:/admin/display/hero";
    }


    @GetMapping("/home/display/hero")
    private String displayHeroUser(Model model) {
        List<Hero> tir1 = new ArrayList<>();
        List<Hero> tir2 = new ArrayList<>();
        List<Hero> tir3 = new ArrayList<>();
        List<Hero> tir4 = new ArrayList<>();
        List<Hero> tir5 = new ArrayList<>();
        List<Hero> tir6 = new ArrayList<>();

        heroService.displayHero(tir1,tir2,tir3,tir4,tir5,tir6);


        model.addAttribute("displayHeroTir1", tir1);
        model.addAttribute("displayHeroTir2", tir2);
        model.addAttribute("displayHeroTir3", tir3);
        model.addAttribute("displayHeroTir4", tir4);
        model.addAttribute("displayHeroTir5", tir5);

        return "menu/button2/user/hero/heroDisplay";
    }

    @GetMapping("/home/display/hero/{id}")
    private String heroDetailsHom(Model model, @PathVariable Long id) {
        viewsService.viewsHero();
        model.addAttribute("detals",heroService.getHeroById(id));

        return "menu/button2/user/hero/heroDetals";
    }

    @GetMapping("/home/detals/hero/{id}")
    private String detalsHome(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("detals", heroService.getHeroById(id));
        return "menu/button2/user/hero/heroDetals";
    }



}
