package com.autumn.controller;

import com.autumn.model.*;
import com.autumn.service.*;
import com.autumn.utils.Navbar;
import com.autumn.utils.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Controller
public class ContratoController {

    @Autowired
    private ContratoService service;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EntidadLegalService entidadLegalService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BufuService bufuService;

    @Autowired
    private HistorialService historialService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private  WorkflowService workflowService;

    /*-----------------------------------------------------*/
    /*----------------Controller Genérico------------------*/
    /*-----------------------------------------------------*/

    //Post Proximo estado UNO
    @PostMapping(value = "/{rol}/contratos/{id}", params = "uno")
    public String postProximoEstado(@PathVariable(name = "id") Long id, @PathVariable(name = "rol") String rol,
                                    Model model){
        Contrato contrato = service.getOne(id);
        Estado proxEstado = estadoService.getOne(contrato.getEstadoActual().getProximoEstado());

        postGenerico(contrato, proxEstado, rol, model);
        return "redirect:/" + rol + "/contratos";

    }

    //POST Proximo estado DOS
    @PostMapping(value = "/{rol}/contratos/{id}", params = "dos")
    public String postProximoEstadoDos(@PathVariable(name = "id") Long id, @PathVariable(name = "rol") String rol,
                                       Model model){
        Contrato contrato = service.getOne(id);
        Estado proxEstado = estadoService.getOne(contrato.getEstadoActual().getProximoEstadoDos());

        postGenerico(contrato, proxEstado, rol, model);

        return "redirect:/" + rol + "/contratos";
    }

    public void postGenerico(Contrato contrato, Estado proxEstado, String rol, Model model){
        //Cambiamos el estado del contrato
        contrato.setEstadoActual(proxEstado);

        //TODO: Cambiar esto...
        //Asignacion de colaboradores
        contrato = asignarColaboradores(contrato);

        //Actualizamos el historial
        crearHistorial(contrato, proxEstado, model);

        //Le damos el poder del contrato a quien corresponda
        model = determinarQuienTieneElContrato(model, contrato);

        //Guardamos el contrato
        service.create(contrato);
    }

    @PostMapping("/{rol}/contratos/{id}/nuevo-chat")
    public String postChat(@PathVariable("id") Long id, @PathVariable("rol") String rol,
                           @RequestParam("chat_mensaje") String mensaje, Model model){
        //El mensaje no puede estar vacío
        if (mensaje.matches(" *")){
            return "redirect:/" + rol + "/contratos/" + id + "?error&emptyChatMessage";
        }

        Contrato contrato = service.getOne(id);
        Chat chat = new Chat();
        chat.setContrato(contrato);
        chat.setMensaje(mensaje);
        chat.setFecha(new Date());
        chat.setUsuario(usuarioService.getUsuarioLogeado());
        chatService.create(chat);
        return "redirect:/" + rol + "/contratos/" + id;
    }

    /*-----------------------------------------------------*/
    /*---------------Controller for Legales----------------*/
    /*-----------------------------------------------------*/

    @GetMapping("/legales/contratos")
    public String getContratosLegales(Model model){

        model.addAttribute("list_contratos", service.getAll());
        model = inicializarComponentesGenerales(model, Navbar.NAVBARGENERAL.getNavbarFlag(), null);
        return "contratos/legales/contratos";
    }

    //Contrato específico (detalle)
    @GetMapping("/legales/contratos/{id}")
    public String getContratosLegalesDetalle(@PathVariable(name = "id") Long id, Model model){
        model = inicializarComponentesGenerales(model, Navbar.NAVBARCONTRATOS.getNavbarFlag(), "legales");

        Contrato c = service.getOne(id);
        if(c.getEstadoActual() != null){
            Estado estadoActual = c.getEstadoActual();
            if (c.getEstadoActual().getProximoEstado() != null){
                model.addAttribute("proximoEstado", estadoService.getOne(estadoActual.getProximoEstado()));
            }
            if (c.getEstadoActual().getProximoEstadoDos() != null){
                model.addAttribute("proximoEstadoDos", estadoService.getOne(estadoActual.getProximoEstadoDos()));
            }
        }
        //Si el contrato no tiene workflow, debe asignarle uno
        if(c.getWorkflow() == null){
            model.addAttribute("list_workflows", workflowService.getAll());
        }

        model = determinarQuienTieneElContrato(model, c);
        model.addAttribute("contrato", c);

        model.addAttribute("list_historial", historialService.getAllByContrato(c));
        model.addAttribute("list_chat", chatService.getAllByContrato(c));

        return "contratos/legales/contratos-detalle";
    }

    @PostMapping("/legales/contratos/workflow")
    public String postContratosLegalesWorkflow(@ModelAttribute("contrato") Contrato contrato, Model model){
        Contrato c = service.getOne(contrato.getId());
        c.setWorkflow(contrato.getWorkflow());
        c.setEstadoActual(estadoService.getFirstByWorkflow(contrato.getWorkflow()));
        service.create(c);
        return "redirect:/legales/contratos";
    }


    /*-----------------------------------------------------*/
    /*---------------Controller for Usuarios---------------*/
    /*-----------------------------------------------------*/

    //Nuevo Contrato
    @GetMapping("/usuario/contratos/nuevo")
    public String getContratosUsuariosNuevo(Model model){

        model = inicializarComponentesGenerales(model, Navbar.NAVBARCONTRATOS.getNavbarFlag(), "usuario");
        model.addAttribute("nuevoContrato", new Contrato());
        model.addAttribute("list_entidadeslegales", entidadLegalService.getAll());
        model.addAttribute("list_ibps", usuarioService.getAllActiveIbps());
        model.addAttribute("list_bufus", bufuService.getAllActive());

        return "contratos/usuario/contratos-nuevo";
    }

    @PostMapping("/usuario/contratos/nuevo")
    public String postContratosUsuariosNuevo(@Valid @ModelAttribute("nuevoContrato") Contrato nuevoContrato,
                                             BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("list_entidadeslegales", entidadLegalService.getAll());
            model.addAttribute("list_ibps", usuarioService.getAllActiveIbps());
            model.addAttribute("list_bufus", bufuService.getAllActive());
            model.addAttribute("nuevoContrato", nuevoContrato);
            model = inicializarComponentesGenerales(model, Navbar.NAVBARCONTRATOS.getNavbarFlag(), "usuario");
            return "contratos/usuario/contratos-nuevo";
        }
        nuevoContrato.setFechaEmision(new Date());
        nuevoContrato.setUsuario(usuarioService.getUsuarioLogeado());

        service.create(nuevoContrato);
        return "redirect:/usuario/contratos";
    }

    //Contratos (tabla)
    @GetMapping("/usuario/contratos")
    public String getContratosUsuarios(Model model){

        model = inicializarComponentesGenerales(model, Navbar.NAVBARGENERAL.getNavbarFlag(), null);
        model.addAttribute("list_contratos", service.getAllByUsuarioLogeado(usuarioService.getUsuarioLogeado()));

        return "contratos/usuario/contratos";
    }

    //Contrato específico (detalle)
    @GetMapping("/usuario/contratos/{id}")
    public String getContratosUsuariosDetalle(@PathVariable(name = "id") Long id, Model model){
        model = inicializarComponentesGenerales(model, Navbar.NAVBARCONTRATOS.getNavbarFlag(), "usuario");

        Contrato c = service.getOne(id);
        if(c.getEstadoActual() != null){
            Estado estadoActual = c.getEstadoActual();
            if (c.getEstadoActual().getProximoEstado() != null){
                model.addAttribute("proximoEstado", estadoService.getOne(estadoActual.getProximoEstado()));
            }
            if (c.getEstadoActual().getProximoEstadoDos() != null){
                model.addAttribute("proximoEstadoDos", estadoService.getOne(estadoActual.getProximoEstadoDos()));
            }
        }

        model = determinarQuienTieneElContrato(model, c);
        model.addAttribute("contrato", c);

        model.addAttribute("list_historial", historialService.getAllByContrato(c));
        model.addAttribute("list_chat", chatService.getAllByContrato(c));

        return "contratos/usuario/contratos-detalle";
    }



    /*-----------------------------------------------------*/
    /*--------------Controller for Impuestos---------------*/
    /*-----------------------------------------------------*/

    @GetMapping("/impuestos/contratos")
    public String getContratosImpuestos(Model model){

        model.addAttribute("list_contratos", service.getAllEnvioImpuestos());
        model = inicializarComponentesGenerales(model, Navbar.NAVBARGENERAL.getNavbarFlag(), null);
        return "contratos/impuestos/contratos";
    }

    //Contrato específico (detalle)
    @GetMapping("/impuestos/contratos/{id}")
    public String getContratosImpuestosDetalle(@PathVariable(name = "id") Long id, Model model){
        model = inicializarComponentesGenerales(model, Navbar.NAVBARCONTRATOS.getNavbarFlag(), "impuestos");

        Contrato c = service.getOne(id);
        if(c.getEstadoActual() != null){
            Estado estadoActual = c.getEstadoActual();
            if (c.getEstadoActual().getProximoEstado() != null){
                model.addAttribute("proximoEstado", estadoService.getOne(estadoActual.getProximoEstado()));
            }
            if (c.getEstadoActual().getProximoEstadoDos() != null){
                model.addAttribute("proximoEstadoDos", estadoService.getOne(estadoActual.getProximoEstadoDos()));
            }
        }

        model = determinarQuienTieneElContrato(model, c);
        model.addAttribute("contrato", c);

        model.addAttribute("list_historial", historialService.getAllByContrato(c));
        model.addAttribute("list_chat", chatService.getAllByContrato(c));

        return "contratos/impuestos/contratos-detalle";
    }

    /*-----------------------------------------------------*/
    /*-------------Controller for Apoderados---------------*/
    /*-----------------------------------------------------*/

    @GetMapping("/apoderados/contratos")
    public String getContratosApoderados(Model model){

        model.addAttribute("list_contratos", service.getAllFirmaApoderados());
        model = inicializarComponentesGenerales(model, Navbar.NAVBARGENERAL.getNavbarFlag(), null);
        return "contratos/apoderados/contratos";
    }

    //Contrato específico (detalle)
    @GetMapping("/apoderados/contratos/{id}")
    public String getContratosApoderadosDetalle(@PathVariable(name = "id") Long id, Model model){
        model = inicializarComponentesGenerales(model, Navbar.NAVBARCONTRATOS.getNavbarFlag(), "apoderados");

        Contrato c = service.getOne(id);
        if(c.getEstadoActual() != null){
            Estado estadoActual = c.getEstadoActual();
            if (c.getEstadoActual().getProximoEstado() != null){
                model.addAttribute("proximoEstado", estadoService.getOne(estadoActual.getProximoEstado()));
            }
            if (c.getEstadoActual().getProximoEstadoDos() != null){
                model.addAttribute("proximoEstadoDos", estadoService.getOne(estadoActual.getProximoEstadoDos()));
            }
        }

        model = determinarQuienTieneElContrato(model, c);
        model.addAttribute("contrato", c);

        model.addAttribute("list_historial", historialService.getAllByContrato(c));
        model.addAttribute("list_chat", chatService.getAllByContrato(c));

        return "contratos/apoderados/contratos-detalle";
    }

    /*-----------------------------------------------------*/
    /*----------------------- UTILS -----------------------*/
    /*-----------------------------------------------------*/

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

    //Determina de que lado se encuentra el contrato y quien puede actuar sobre este. Los demás roles no
    //podrán ejecutar acciones si no les corresponde
    private Model determinarQuienTieneElContrato(Model model, Contrato contrato){
        if(contrato.getEstadoActual() != null) {
            switch (contrato.getEstadoActual().getNombre()) {
                case "Nuevo Contrato":
                case "Revisión Usuario":
                case "Envío Versión Final":
                case "Envío Carta Aceptación":
                case "Envío Aceptación Sellada":
                case "Envío Oferta Validada":
                case "Envío Oferta Sellada":
                case "Recepción Acuerdo":
                case "Recepción Acuerdo Firmado":
                    model.addAttribute("quienTieneContrato", Rol.USUARIO.getRol());
                    break;
                case "Revisión Legales":
                case "Archivo":
                case "Finalizado":
                    model.addAttribute("quienTieneContrato", Rol.IBP.getRol());
                    break;
                case "Envío Impuestos":
                case "Revisión Impuestos":
                    model.addAttribute("quienTieneContrato", Rol.IMPUESTOS.getRol());
                    break;
                case "Firma Apoderados":
                    model.addAttribute("quienTieneContrato", Rol.APODERADO.getRol());
                    break;
                case "Envío Aceptación Firmada":
                    if (contrato.getWorkflow().getWorkflow().equalsIgnoreCase(Workflow.CARTA_OFERTA_CONTRAPARTE.getWorkflow())) {
                        model.addAttribute("quienTieneContrato", Rol.USUARIO.getRol());
                    } else if (contrato.getWorkflow().getWorkflow().equalsIgnoreCase(Workflow.CARTA_OFERTA_BASF.getWorkflow())) {
                        model.addAttribute("quienTieneContrato", Rol.IBP.getRol());
                    }
                    break;
                case "Envío Oferta Firmada":
                    if (contrato.getWorkflow().getWorkflow().equalsIgnoreCase(Workflow.CARTA_OFERTA_CONTRAPARTE.getWorkflow())) {
                        model.addAttribute("quienTieneContrato", Rol.IBP.getRol());
                    } else if (contrato.getWorkflow().getWorkflow().equalsIgnoreCase(Workflow.CARTA_OFERTA_BASF.getWorkflow())) {
                        model.addAttribute("quienTieneContrato", Rol.USUARIO.getRol());
                    }
                    break;
            }
        } else {
            model.addAttribute("quienTieneContrato", Rol.IBP.getRol());
        }
        return model;
    }

    //Crea Historial cada vez q se cambia de estado o sea necesario
    private void crearHistorial(Contrato contrato, Estado enviadoA, Model model){
        Historial historial = new Historial();
        historial.setFecha(new Date());
        historial.setContrato(contrato);
        String nombreUsuario = usuarioService.getUsuarioLogeado().getNombre() + " " + usuarioService.getUsuarioLogeado().getApellido();

        historial.setDescripcion(nombreUsuario +" ha enviado el contrato a <" + enviadoA.getNombre() + ">");

        historialService.create(historial);
    }

    private Contrato asignarColaboradores(Contrato contrato) {
        switch (contrato.getEstadoActual().getNombre()){
            case "Envío Aceptación Sellada":
                contrato.setFirmaImpuestos(usuarioService.getUsuarioLogeado());
                contrato.setFechaFirmaImpuestos(new Date());
                break;
            case "Envío Aceptación Firmada":
                contrato.setFirmaApoderadoUno(usuarioService.getUsuarioLogeado());
                contrato.setFirmaApoderadoDos(usuarioService.getUsuarioLogeado());
                contrato.setFechaFirmaApoderadoUno(new Date());
                contrato.setFechaFirmaApoderadoDos(new Date());
                break;
            case "Envío Oferta Firmada":
                contrato.setFirmaContraparte(usuarioService.getUsuarioLogeado());
                contrato.setFechaFirmaUsuario(new Date());
                break;
            case "Envío Carta Aceptación":
                contrato.setFirmaLegales(usuarioService.getUsuarioLogeado());
                contrato.setFechaFirmaLegales(new Date());
                break;
        }

        return contrato;
    }
}
