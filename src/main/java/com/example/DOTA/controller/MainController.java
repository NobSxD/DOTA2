package com.example.DOTA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "menu/button1/autoChess";
    }

    @GetMapping("/home")
    public String home(){
        return "menu/button1/autoChess";
    }

    @GetMapping("/admin")
    public String adminHome(){
        return "menu/button1/autoChessAdmin";
    }


}
