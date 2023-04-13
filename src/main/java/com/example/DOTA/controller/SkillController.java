package com.example.DOTA.controller;

import com.example.DOTA.services.SkillService;
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
public class SkillController {
    private final SkillService skillService;


    @GetMapping("/admin/add/skill")
    private String add() {
        return "menu/button2/admin/skill/skillAdd";
    }

    @PostMapping("/admin/add/skill")
    private String save(Model model,
                       @RequestParam String classHero,
                       @RequestParam String full_text,
                       @RequestParam("iconClassHero") MultipartFile file1) throws IOException {


        skillService.saveSkill(file1, classHero, full_text);
        return "redirect:/admin/add/skill";
    }


    @GetMapping("/admin/display/skill")
    private String displaySkill(Model model) {
        model.addAttribute("skill", skillService.listSkill());
        return "menu/button2/admin/skill/skillDisplay";
    }

    @GetMapping("/admin/detals/skill/{id}")
    private String detalsSkill(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("skill", skillService.skill(id));
        return "menu/button2/admin/skill/skillDetals";
    }


    @GetMapping("/admin/delete/skill/{id}")
    private String deleteSkill(@PathVariable(value = "id") Long id) {
        skillService.deleteSkill(skillService.skill(id));
        return "redirect:/admin/display/skill";
    }

    @GetMapping("/admin/edit/skill/{id}")
    private String editSkill(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("skill", skillService.skill(id));
        return "menu/button2/admin/skill/skillEdit";
    }

    @PostMapping("/admin/edit/skill/{id}")
    private String update(@PathVariable(value = "id") Long id, @RequestParam String className, @RequestParam String detals, @RequestParam("iconClassHero") MultipartFile multipartFile) throws IOException {
        skillService.updateSkill(multipartFile, className, detals, id);
        return "redirect:/admin/display/skill";
    }


    @GetMapping("/home/display/skill")
    private String displaySkillUser(Model model) {
        model.addAttribute("skill", skillService.listSkill());
        return "menu/button2/user/skill/skillDisplay";
    }

    @GetMapping("/home/detals/skill/{id}")
    private String detalsSkillUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("id", skillService.skill(id));
        return "menu/button2/user/skill/skillDetals";
    }
}
