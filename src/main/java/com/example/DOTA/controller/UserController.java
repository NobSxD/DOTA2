package com.example.DOTA.controller;

import com.example.DOTA.models.User;
import com.example.DOTA.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/home/error")
    public String error(){
        return "/user/error";
    }

    @GetMapping("/admin/users")
    public String test(Model model){
        model.addAttribute("list", userService.list());
        return "user/listUser";
    }
    @GetMapping("/admin/delete/role/{id}")
    private String deleteUsers(){
        return "redirect:/admin/users";
    }
    @PostMapping("/admin/delete/role/{id}")
    private String deleteUsersRolee(@PathVariable(value = "id") Long id, @RequestParam String deleteRol){
        userService.deleteRole(id, deleteRol);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/add/role/{id}")
    private String addUsers(){
        return "redirect:/admin/users";
    }
    @PostMapping("/admin/add/role/{id}")
    private String addUsersRolee(@PathVariable(value = "id") Long id, @RequestParam String addRol){
        userService.addRole(id, addRol);
        return "redirect:/admin/users";
    }



    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "user/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "user/register";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getName() + " уже существует");
            return "user/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        return "user-info";
    }

}
