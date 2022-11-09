package com.autumn.controller;

import com.autumn.service.ContratoService;
import com.autumn.service.UsuarioService;
import com.autumn.utils.Navbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfiguracionesYReportesController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContratoService contratoService;

    @GetMapping("/legales/configuracion")
    public String getConfiguraciones(Model model){

        model = inicializarComponentesGenerales(model, Navbar.NAVBARGENERAL.getNavbarFlag(), null);
        return "/legales/configuracion/configuraciones-menu";
    }

    @GetMapping("/legales/reportes")
    public String getReportes(Model model){

        model = inicializarComponentesGenerales(model, Navbar.NAVBARGENERAL.getNavbarFlag(), null);
        model.addAttribute("r_contratos_finalizados", contratoService.getCountContratosSegunEstado("Finalizado"));
        model.addAttribute("r_contratos_en_curso", contratoService.getCountContratosSegunEstadoNoEsIgual("Finalizado"));
        return "/contratos/legales/reportes";
    }

    //Inicializa componentes como la Navbar y el Usuario Logeado
    private Model inicializarComponentesGenerales(Model model, String navbarFlag, String navbar_rol){
        model.addAttribute("navbarFlag", navbarFlag);
        if (!model.containsAttribute("actualUser")){
            model.addAttribute("actualUser", usuarioService.getUsuarioLogeado());
        }
        if (navbar_rol != null){
            model.addAttribute("navbar_rol", navbar_rol);
        }
        return model;
    }
}
