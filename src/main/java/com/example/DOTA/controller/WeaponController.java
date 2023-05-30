package com.example.DOTA.controller;

import com.example.DOTA.models.Weapon;
import com.example.DOTA.services.ViewsService;
import com.example.DOTA.services.WeaponService;
import lombok.RequiredArgsConstructor;
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

    private final WeaponService weaponService;
    private final ViewsService viewsService;
    @GetMapping("/admin/add/items")
    private String items(Model model){
        model.addAttribute("items", weaponService.weaponAll());
        return "menu/button2/admin/items/itemsAdd";
    }


    @PostMapping("/admin/add/items")
    private String save(Model model,
                       @RequestParam String weapon,
                       @RequestParam String items1,
                       @RequestParam String items2,
                       @RequestParam String items3,
                       @RequestParam String tirWeapon,
                       @RequestParam String passiveSkill,
                       @RequestParam String activeSkill,
                       @RequestParam String rangeAttack,
                       @RequestParam String kd,
                       @RequestParam("iconWeapon") MultipartFile file1) throws IOException {
        weaponService.saveWeapon(weapon,tirWeapon,passiveSkill,activeSkill,rangeAttack,kd,items1,items2,items3, file1);
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
        weaponService.deleteItems(id);
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
    public String editHero(@PathVariable(value = "id") long id,
                           @RequestParam String weapon,
                           @RequestParam String items1,
                           @RequestParam String items2,
                           @RequestParam String items3,
                           @RequestParam String tirWeapon,
                           @RequestParam String passiveSkill,
                           @RequestParam String activeSkill,
                           @RequestParam String rangeAttack,
                           @RequestParam String kd,
                           @RequestParam("iconWeapon") MultipartFile file1) throws IOException {
        if (!weaponService.weaponExistById(id)) {
            return "redirect:/admin/display/items";
        }

        weaponService.editWeapon(weapon,tirWeapon,passiveSkill,activeSkill,rangeAttack,kd,items1,items2,items3, file1, id);

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
        return "menu/button2/user/items/itemsDetals";
    }
}
