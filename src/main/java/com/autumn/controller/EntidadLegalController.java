package com.autumn.controller;

import com.autumn.model.EntidadLegal;
import com.autumn.service.EntidadLegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EntidadLegalController {

    @Autowired
    private EntidadLegalService service;

    @GetMapping("/legales/configuracion/entidadeslegales")
    public String getEntidadesLegales(){
        return "legales/configuracion/entidadeslegales";
    }

    @PostMapping("/legales/configuracion/entidadeslegales")
    public void postEntidadesLegales(@ModelAttribute EntidadLegal entidadLegal){
        service.create(entidadLegal);
    }
}
