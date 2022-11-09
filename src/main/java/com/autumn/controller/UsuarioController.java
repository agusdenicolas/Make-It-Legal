package com.autumn.controller;

import com.autumn.model.Usuario;
import com.autumn.service.EntidadLegalService;
import com.autumn.service.UsuarioService;
import com.autumn.utils.Navbar;
import com.autumn.utils.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private EntidadLegalService entidadLegalService;

    //----------REGISTER Y LOGIN----------

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

    //----------CONFIGURACION USUARIOS (Perfiles)----------

    @GetMapping("/legales/configuracion/usuarios")
    public String getUsuarios(Model model){
        //Usuarios con Rol != "Usuario" y Rol != " "
        model.addAttribute("list_usuarios", service.getAllNotRolUsuarioAndNotRolNull());
        //Usuarios con Rol == " "
        model.addAttribute("list_nuevos_usuarios", service.getAllRolNull());
        //Lista de Roles
        model.addAttribute("list_roles", Rol.getRolesNotUsuario());
        //Lista de Entidades Legales
        model.addAttribute("list_entidadeslegales", entidadLegalService.getAll());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("http_method", "post");
        model = inicializarComponentesGenerales(model, Navbar.NAVBARGENERAL.getNavbarFlag(), "legales");

        return "/legales/configuracion/usuarios";
    }

    @GetMapping("/legales/configuracion/usuarios/{id}")
    public String getUsuarioEdit(@PathVariable(name = "id") Long id, Model model){
        //Usuarios con Rol != "Usuario" y Rol != " "
        model.addAttribute("list_usuarios", service.getAllNotRolUsuarioAndNotRolNull());
        //Lista de Roles
        model.addAttribute("list_roles", Rol.getRolesNotUsuario());
        //Lista de Entidades Legales
        model.addAttribute("list_entidadeslegales", entidadLegalService.getAll());
        model.addAttribute("usuario", service.getOne(id));
        model.addAttribute("http_method", "put");
        model = inicializarComponentesGenerales(model, Navbar.NAVBARGENERAL.getNavbarFlag(), "legales");

        return "/legales/configuracion/usuarios";
    }

    @PostMapping("/legales/configuracion/usuarios")
    public String addUser(@ModelAttribute("usuario") Usuario usuario){
        if (usuario.getId() == null){
            return "redirect:/legales/configuracion/usuarios?error&NotUserSelected";
        }

        Usuario newUsuario = service.getOne(usuario.getId());

        if (usuario.getRol() == ""){
            return "redirect:/legales/configuracion/usuarios?error&rolNull";
        }

        newUsuario.setIsActivo(usuario.getIsActivo());
        newUsuario.setRol(usuario.getRol());
        newUsuario.setEntidadLegal(usuario.getEntidadLegal());
        service.update(newUsuario);
        return "redirect:/legales/configuracion/usuarios";
    }

    @PutMapping("/legales/configuracion/usuarios")
    public String updateUsuario(@ModelAttribute Usuario usuario){
        if (usuario.getRol() == ""){
            return "redirect:/legales/configuracion/usuarios/" + usuario.getId() + "?error&rolNull";
        }

        service.update(usuario);
        return "redirect:/legales/configuracion/usuarios";
    }

    //Inicializa componentes como la Navbar y el Usuario Logeado
    private Model inicializarComponentesGenerales(Model model, String navbarFlag, String navbar_rol){
        model.addAttribute("navbarFlag", navbarFlag);
        if (!model.containsAttribute("actualUser")){
            model.addAttribute("actualUser", service.getUsuarioLogeado());
        }
        if (navbar_rol != null){
            model.addAttribute("navbar_rol", navbar_rol);
        }
        return model;
    }
}
