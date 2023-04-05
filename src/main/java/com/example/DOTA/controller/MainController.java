package com.example.DOTA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "userMenuTop/button1/autoChess";
    }

    @GetMapping("/home")
    public String home(){
        return "userMenuTop/button1/autoChess";
    }


}
