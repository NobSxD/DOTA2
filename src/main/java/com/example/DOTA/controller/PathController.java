package com.example.DOTA.controller;

import com.example.DOTA.services.PathService;
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
public class PathController {
    private final PathService pathService;

    @GetMapping("/admin/add/path")
    private String add(Model model){
        return "menu/button4/admin/path/pathAdd";
    }

    @PostMapping("/admin/add/path")
    private String save(Model model,
                        @RequestParam String name,
                        @RequestParam String full_text) throws IOException {
        pathService.savePath(name, full_text);
        return "redirect:/admin/add/path";
    }

    @GetMapping("/admin/display/path")
    private String display(Model model){
        model.addAttribute("path" ,pathService.pathAll());
        return "menu/button4/admin/path/pathDisplay";
    }

    @GetMapping("/admin/delete/path/{id}")
    private String delete(@PathVariable(value = "id")Long id){
        pathService.deletePath(id);
        return "redirect:/admin/display/path";
    }

    @GetMapping("/admin/edit/path/{id}")
    private String edit(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("path",pathService.pathById(id));
        return "menu/button4/admin/path/pathEdit";
    }
    @PostMapping("/admin/edit/path/{id}")
    private String update(@PathVariable(value = "id")Long id, @RequestParam String name, @RequestParam String full_text) throws IOException {
        pathService.editPath(id,name, full_text);
        return "redirect:/admin/display/path";
    }

    @GetMapping("/home/display/path")
    private String displayHome(Model model){
        model.addAttribute("path" ,pathService.pathAll());
        return "menu/button4/user/path/pathDisplay";
    }
}
