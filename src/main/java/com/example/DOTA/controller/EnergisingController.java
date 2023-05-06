package com.example.DOTA.controller;

import com.example.DOTA.services.EnergisingService;
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

@Controller
@RequiredArgsConstructor
public class EnergisingController {
    private final EnergisingService energisingService;
    private final ViewsService viewsService;

    @GetMapping("/admin/add/energising")
    private String energisingAdd() {
        return "menu/button5/admin/energising/energisingAdd";
    }

    @PostMapping("/admin/add/energising")
    private String energisingSave(@RequestParam String name, @RequestParam String full_text, @RequestParam("iconEnergising") MultipartFile file1
    )throws IOException {
    energisingService.energisingParameter(name,full_text,file1);
        return "redirect:/admin/add/energising";
    }

    @GetMapping("/admin/display/energising")
    private String display(Model model){
        model.addAttribute("energising" ,energisingService.allEnergising());
        return "menu/button5/admin/energising/energisingDisplay";
    }

    @GetMapping("/admin/detals/energising/{id}")
    private String detalsEnergising(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("energising", energisingService.ByIdEnergising(id));
        return "menu/button5/admin/energising/energisingDetals";
    }

    @GetMapping("/admin/delete/energising/{id}")
    private String deleteEnergising(@PathVariable(value = "id") Long id){
        energisingService.energisingDelete(id);
        return "redirect:/admin/display/energising";
    }

    @GetMapping("/admin/edit/energising/{id}")
    private String update(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("energising", energisingService.ByIdEnergising(id));
        return "menu/button5/admin/energising/energisingEdit";
    }

    @PostMapping("/admin/edit/energising/{id}")
    private String update(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String full_text,
                          @RequestParam("iconEnergising") MultipartFile file1) throws IOException {
        energisingService.energisingParameterEdit(id,name,full_text,file1);
        return "redirect:/admin/display/energising";
    }

    @GetMapping("/home/display/energising")
    private String displayHome(Model model){
        viewsService.viewsEnergising();
        model.addAttribute("energising" ,energisingService.allEnergising());
        return "menu/button5/user/energising/energisingDisplay";
    }

    @GetMapping("/home/detals/energising/{id}")
    private String detalsEnergisingHome(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("energising", energisingService.ByIdEnergising(id));
        return "menu/button5/user/energising/energisingDetals";
    }
}
