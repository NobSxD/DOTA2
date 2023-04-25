package com.example.DOTA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class playController {
    @GetMapping("/home/display/whatgame")
    public String play(){
        return "/menu/button3/user/play";
    }

}
