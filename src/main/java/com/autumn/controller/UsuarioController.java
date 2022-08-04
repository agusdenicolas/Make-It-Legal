package com.autumn.controller;

import com.autumn.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(ModelMap model){
        boolean validaciones [] = service.register("asd");
        //If el usuario ya existe
        if (validaciones[0]){
            model.addAttribute("userExists", true);
            return new ModelAndView("redirect:/register?error", model);
        }
        //Si el usuario no es *@basf.com
        if(validaciones[1]){
            model.addAttribute("userWrongDomain", true);
            return new ModelAndView("redirect:/register?error", model);
        }
        return new ModelAndView("redirect:/login", model);

    }
}
