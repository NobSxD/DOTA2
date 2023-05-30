package com.example.DOTA.controller;

import com.example.DOTA.services.ViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class playController {
    private final ViewsService viewsService;
    @GetMapping("/home/display/whatgame")
    public String play(){
        viewsService.viewsPlay();
        return "menu/button3/user/play";
    }

}
