package com.example.DOTA.controller;

import com.example.DOTA.models.Hero;
import com.example.DOTA.repository.ViewsRepository;
import com.example.DOTA.services.*;
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
    private final EnergisingService energisingService;
    private final ViewsService viewsService;



    @GetMapping("/admin/add/hero")
    private String heroAdd(Model model) {
        model.addAttribute("ras", energisingService.allEnergising());

        return "menu/button2/admin/hero/heroAdd";
    }

    @PostMapping("/admin/add/hero")
    private String saveHero(Model model,
                            @RequestParam String nameHero,
                            @RequestParam String tirHero,
                            @RequestParam String classHero,
                            @RequestParam String classHero1,
                            @RequestParam String classHero2,
                            @RequestParam String max_xp,
                            @RequestParam String max_mp,
                            @RequestParam String damage,
                            @RequestParam String speed_damage,
                            @RequestParam String range_attack,
                            @RequestParam String armor,
                            @RequestParam String mag_resist,
                            @RequestParam String name_skill,
                            @RequestParam String activation_skill,
                            @RequestParam String full_text,
                            @RequestParam String detals_skill1,
                            @RequestParam String detals_skill2,
                            @RequestParam String detals_skill3,
                            @RequestParam String detals_skill4,
                            @RequestParam String detals_skill5,
                            @RequestParam String detals_skill6,
                            @RequestParam String cast_mp,
                            @RequestParam String kd_skill,
                            @RequestParam("iconHero") MultipartFile file1,
                            @RequestParam("iconHero1") MultipartFile file2
    )throws IOException {
        heroService.saveHero(nameHero,tirHero,classHero,classHero1,classHero2,max_xp,max_mp,damage,speed_damage,
                range_attack, armor,mag_resist,name_skill, activation_skill,full_text,detals_skill1,detals_skill2,
                detals_skill3,detals_skill4,detals_skill5,detals_skill6,cast_mp,kd_skill,file1,file2);
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
                            @RequestParam String nameHero,
                            @RequestParam String tirHero,
                            @RequestParam String classHero,
                            @RequestParam String classHero1,
                            @RequestParam String classHero2,
                            @RequestParam String max_xp,
                            @RequestParam String max_mp,
                            @RequestParam String damage,
                            @RequestParam String speed_damage,
                            @RequestParam String range_attack,
                            @RequestParam String armor,
                            @RequestParam String mag_resist,
                            @RequestParam String name_skill,
                            @RequestParam String activation_skill,
                            @RequestParam String full_text,
                            @RequestParam String detals_skill1,
                            @RequestParam String detals_skill2,
                            @RequestParam String detals_skill3,
                            @RequestParam String detals_skill4,
                            @RequestParam String detals_skill5,
                            @RequestParam String detals_skill6,
                            @RequestParam String cast_mp,
                            @RequestParam String kd_skill,
                            @RequestParam("iconHero") MultipartFile file1,
                            @RequestParam("iconHero1") MultipartFile file2) throws IOException {
        if (!heroService.heroExistById(id)) {
            return "redirect:/admin/display/hero";
        }

        heroService.editHero(id,nameHero,tirHero,classHero,classHero1,classHero2,max_xp,max_mp,damage,speed_damage,
                range_attack, armor,mag_resist,name_skill, activation_skill,full_text,detals_skill1,detals_skill2,
                detals_skill3,detals_skill4,detals_skill5,detals_skill6,cast_mp,kd_skill,file1,file2);

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
