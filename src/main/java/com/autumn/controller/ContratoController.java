package com.autumn.controller;

import com.autumn.model.Estado;
import com.autumn.model.Workflow;
import com.autumn.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/legales/contratos")
public class ContratoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public String getContratos(Model model){


        return "legales/contratos";
    }
}
