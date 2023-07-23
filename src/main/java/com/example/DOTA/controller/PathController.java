package com.example.DOTA.controller;

import com.example.DOTA.services.PathService;
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
public class PathController {
    private final PathService pathService;
    private final ViewsService viewsService;

    @GetMapping("/admin/add/path")
    private String add(Model model){
        return "menu/button4/admin/path/pathAdd";
    }

    @PostMapping("/admin/add/path")
    private String save(Model model,
                        @RequestParam String name,
                        @RequestParam String full_text,
                        @RequestParam("fon") MultipartFile file) throws IOException {
        pathService.savePath(name, full_text, file);
        return "redirect:/admin/add/path";
    }

    @GetMapping("/admin/display/path")
    private String display(Model model){
        model.addAttribute("path" ,pathService.pathAll());
        return "menu/button4/admin/path/pathDisplay";
    }
    @GetMapping("/admin/detals/path/{id}")
    private String detals(Model model,@PathVariable(value = "id") Long id){
        viewsService.viewsPath();
        model.addAttribute("path" ,pathService.pathById(id));
        return "menu/button4/admin/path/pathDetals";
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
    private String update(@PathVariable(value = "id")Long id, @RequestParam String name, @RequestParam String full_text,  @RequestParam("fon") MultipartFile file) throws IOException {
        pathService.editPath(id,name, full_text, file);
        return "redirect:/admin/display/path";
    }

    @GetMapping("/home/display/path")
    private String displayHome(Model model){
        viewsService.viewsPath();
        model.addAttribute("path" ,pathService.pathAll());
        return "menu/button4/user/path/pathDisplay";
    }
    @GetMapping("/home/detals/path/{id}")
    private String detalsPath(Model model,@PathVariable(value = "id") Long id){
        viewsService.viewsPath();
        model.addAttribute("path" ,pathService.pathById(id));
        return "menu/button4/user/path/pathDetals";
    }

    @GetMapping("/home")
    private String displayMainHome(Model model){
        viewsService.viewsPath();
        model.addAttribute("path" ,pathService.pathAll());
        return "menu/button1/autoChess";
    }
    @GetMapping("/")
    private String displayMain(Model model){
        viewsService.viewsPath();
        model.addAttribute("path" ,pathService.pathAll());
        return "menu/button1/autoChess";
    }
}
