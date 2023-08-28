package com.example.DOTA.controller;

import com.example.DOTA.models.Weapon;
import com.example.DOTA.services.GuideService;
import com.example.DOTA.services.ViewsService;
import com.example.DOTA.services.WeaponService;
import com.example.DOTA.services.imageServices.SaveImageFileSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WeaponController {

    @Value("${upload.path}")
    private String uploadPath;

    private final WeaponService weaponService;
    private final ViewsService viewsService;
    @GetMapping("/admin/add/items")
    private String items(){
        return "menu/button2/admin/items/itemsAdd";
    }


    @PostMapping("/admin/add/items")
    private String save(Weapon weapon,
                       @RequestParam("iconWeapon") MultipartFile file1) throws IOException {
        weapon.setIcon(SaveImageFileSystem.multiple(file1, uploadPath ));
        weaponService.saveWeapon(weapon);
        return "redirect:/admin/add/items";
    }

    @GetMapping("/admin/display/items")
    private String displayItemsAdmin(Model model) {
        List<Weapon> tir1 = weaponService.weaponsTir("ТИР 1");
        List<Weapon> tir2 = weaponService.weaponsTir("ТИР 2");
        List<Weapon> tir3 = weaponService.weaponsTir("ТИР 3");
        List<Weapon> tir4 = weaponService.weaponsTir("ТИР 4");
        List<Weapon> tir5 = weaponService.weaponsTir("ТИР 5");




        model.addAttribute("displayItemsTir1", tir1);
        model.addAttribute("displayItemsTir2", tir2);
        model.addAttribute("displayItemsTir3", tir3);
        model.addAttribute("displayItemsTir4", tir4);
        model.addAttribute("displayItemsTir5", tir5);



        return "menu/button2/admin/items/itemsDisplay";
    }
    @GetMapping("/admin/delete/items/{id}")
    private String deleteItems(@PathVariable(value = "id") Long id){
        weaponService.deleteItems(id, uploadPath);
        return "redirect:/admin/display/items";
    }

    @GetMapping("/admin/detals/items/{id}")
    private String detalsSkill(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("detals", weaponService.weaponID(id));
        return "menu/button2/admin/items/itemsDetals";
    }

    @GetMapping("/admin/edit/items/{id}")
    public String editIdHero(@PathVariable(value = "id") Long id, Model model){
        Weapon weapons = weaponService.weaponID(id);
        model.addAttribute("items", weapons);
        return "menu/button2/admin/items/itemsEdit";
    }

    @PostMapping("/admin/edit/items/{id}")
    public String editHero(Weapon weapon,
                           @RequestParam("iconWeapon") MultipartFile file1) throws IOException {

        weapon.setIcon(SaveImageFileSystem.multiple(file1, uploadPath));
        weaponService.editWeapon(weapon);

        return "redirect:/admin/display/items";
    }

    @GetMapping("/home/display/items")
    private String displayItemsHome(Model model) {
        List<Weapon> tir1 = weaponService.weaponsTir("ТИР 1");
        List<Weapon> tir2 = weaponService.weaponsTir("ТИР 2");
        List<Weapon> tir3 = weaponService.weaponsTir("ТИР 3");
        List<Weapon> tir4 = weaponService.weaponsTir("ТИР 4");
        List<Weapon> tir5 = weaponService.weaponsTir("ТИР 5");




        model.addAttribute("displayItemsTir1", tir1);
        model.addAttribute("displayItemsTir2", tir2);
        model.addAttribute("displayItemsTir3", tir3);
        model.addAttribute("displayItemsTir4", tir4);
        model.addAttribute("displayItemsTir5", tir5);



        return "menu/button2/user/items/itemsDisplay";
    }

    @GetMapping("/home/detals/items/{id}")
    private String detalsSkillHome(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("detals", weaponService.weaponID(id));
        
        viewsService.viewsItems();
        return "menu/button2/user/items/detalsItems";
    }
}
