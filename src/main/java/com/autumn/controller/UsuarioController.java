package com.autumn.controller;

import com.autumn.model.Usuario;
import com.autumn.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView register(ModelMap model, @ModelAttribute Usuario usuario){
        boolean validaciones [] = service.register(usuario);
        //If el usuario ya existe
        if (validaciones[0]){
            return new ModelAndView("redirect:/register?error&userExists", model);
        }
        //Si el usuario no es *@basf.com
        if(validaciones[1]){
            return new ModelAndView("redirect:/register?error&userWrongDomain", model);
        }
        return new ModelAndView("redirect:/login", model);
    }
}
