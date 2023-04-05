package com.example.DOTA.controller;

import com.example.DOTA.models.Weapon;
import com.example.DOTA.repository.WeaponRepository;
import com.example.DOTA.services.WeaponService;
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
public class WeaponController {
    private final WeaponRepository weaponRepository;
    private final WeaponService weaponService;
    @GetMapping("/admin/weapon")
    public String hero(Model model){

        return "userMenuTop/button2/admin/add/WeaponAdd";
    }

    @PostMapping("/admin/weapon")
    public String save(Model model,
                       @RequestParam String weapon,
                       @RequestParam String species,
                       @RequestParam String tirWeapon,
                       @RequestParam String full_text,
                       @RequestParam("iconWeapon") MultipartFile file1,
                       @RequestParam("iconWeapon2") MultipartFile file2,
                       @RequestParam("iconWeapon3") MultipartFile file3,
                       @RequestParam("iconWeapon3") MultipartFile file4) throws IOException {
        Weapon weaponNew = new Weapon(weapon,species, tirWeapon,full_text);
        weaponRepository.save(weaponNew);

        weaponService.saveWeapon(weaponNew,file1,file2,file3,file4);
        return "redirect:/admin/weapon";
    }
}
