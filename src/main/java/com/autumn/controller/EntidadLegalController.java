package com.autumn.controller;

import com.autumn.model.EntidadLegal;
import com.autumn.service.EntidadLegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/legales/configuracion/entidadeslegales")
public class EntidadLegalController {

    @Autowired
    private EntidadLegalService service;

    @GetMapping
    public String getEntidadesLegales(Model model){
        model.addAttribute("list_entidadeslegales", service.getAll());
        model.addAttribute("entidadLegal", new EntidadLegal());
        model.addAttribute("http_method", "post");

        return "/legales/configuracion/entidadeslegales";
    }

    @PostMapping
    public String postEntidadesLegales(@Valid @ModelAttribute("entidadLegal") EntidadLegal entidadLegal,
                                                       BindingResult result, ModelMap model){
        if (result.hasErrors()){
            model.addAttribute("list_entidadeslegales", service.getAll());
            model.addAttribute("entidadLegal", entidadLegal);
            model.addAttribute("http_method", "post");
            return "/legales/configuracion/entidadeslegales";
        }

        boolean alreadyExists = service.create(entidadLegal);

        if(alreadyExists){
            return "redirect:/legales/configuracion/entidadeslegales?error&entidadLegalExists";
        }

        return "redirect:/legales/configuracion/entidadeslegales";
    }

    @GetMapping("/{id}")
    public String getEntidadesLegalesEdit(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("list_entidadeslegales", service.getAll());
        model.addAttribute("entidadLegal", service.getOne(id));
        model.addAttribute("http_method", "put");

        return "/legales/configuracion/entidadeslegales";
    }

    @PutMapping()
    public String putEntidadesLegales(Model model, @ModelAttribute EntidadLegal entidadLegal){
        boolean alreadyExists = service.update(entidadLegal);

        if(alreadyExists){
            return "redirect:/legales/configuracion/entidadeslegales?error&entidadLegalExists";
        }

        return "redirect:/legales/configuracion/entidadeslegales";
    }

/*    @GetMapping("/delete/{id}")
    public String deleteEntidadesLegales(Model model, @PathVariable(name = "id") Long id){
        //service.update(entidadLegal);
        System.out.println("DELETE: " + id);
        return "redirect:/legales/configuracion/entidadeslegales";
    }*/
}
