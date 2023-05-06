package com.example.DOTA.controller;

import com.example.DOTA.services.ViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewsController {
    private final ViewsService viewsService;
    @GetMapping("/admin/views")
    private String views(Model model){
        model.addAttribute("views", viewsService.viewsList());
        return "menu/button1/views";
    }
}
