package com.example.DOTA.controller;

import com.example.DOTA.models.Skill;
import com.example.DOTA.repository.SkillRepository;
import com.example.DOTA.services.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class SkillController {
    private final SkillRepository skillRepository;
    private final SkillService skillService;




    @GetMapping("/admin/skill")
    public String hero(Model model){

        return "SkillAdd";
    }

    @PostMapping("/admin/skill")
    public String save(Model model,
                       @RequestParam String skill,
                       @RequestParam String full_text,
                       @RequestParam("iconSkill") MultipartFile file1) throws IOException {
        Skill skillNew = new Skill(skill,full_text);
        skillRepository.save(skillNew);

        skillService.saveSkill(skillNew,file1);
        return "redirect:/add/skill";
    }
}
