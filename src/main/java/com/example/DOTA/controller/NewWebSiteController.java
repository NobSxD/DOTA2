package com.example.DOTA.controller;

import com.example.DOTA.services.NewWebSiteService;
import com.example.DOTA.services.PathService;
import com.example.DOTA.services.ViewsService;
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
public class NewWebSiteController {
    private final NewWebSiteService newWebSiteService;
    private final ViewsService viewsService;

    @GetMapping("/admin/add/newWebSite")
    private String add(Model model){
        return "menu/button4/admin/newWebSite/newWebSiteAdd";
    }

    @PostMapping("/admin/add/newWebSite")
    private String save(Model model,
                        @RequestParam String name,
                        @RequestParam String full_text) throws IOException {
        newWebSiteService.saveNewWebSite(name, full_text);
        return "redirect:/admin/add/newWebSite";
    }

    @GetMapping("/admin/display/newWebSite")
    private String display(Model model){
        model.addAttribute("path" , newWebSiteService.pathAll());
        return "menu/button4/admin/newWebSite/newWebSiteDisplay";
    }

    @GetMapping("/admin/delete/newWebSite/{id}")
    private String delete(@PathVariable(value = "id")Long id){
        newWebSiteService.deleteNewWebSite(id);
        return "redirect:/admin/display/newWebSite";
    }

    @GetMapping("/admin/edit/newWebSite/{id}")
    private String edit(@PathVariable(value = "id")Long id, Model model){
        model.addAttribute("path", newWebSiteService.newWebSiteById(id));
        return "menu/button4/admin/newWebSite/newWebSiteEdit";
    }
    @PostMapping("/admin/edit/newWebSite/{id}")
    private String update(@PathVariable(value = "id")Long id, @RequestParam String name, @RequestParam String full_text) throws IOException {
        newWebSiteService.editNewWebSite(id,name, full_text);
        return "redirect:/admin/display/newWebSite";
    }
    @GetMapping("/home/display/newWebSite")
    private String displayHome(Model model){
        viewsService.viewsNewWeb();
        model.addAttribute("path" , newWebSiteService.pathAll());
        return "menu/button4/user/newWebSite/newWebSiteDisplay";
    }
}
