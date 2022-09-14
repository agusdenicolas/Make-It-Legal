package com.autumn.controller;

import com.autumn.model.BUFU;
import com.autumn.model.Usuario;
import com.autumn.service.BufuService;
import com.autumn.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/legales/configuracion/bufu-ibp")
public class BufuController {

    @Autowired
    private BufuService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String getBufus(Model model){
        model.addAttribute("list_bufu", service.getAll());
        model.addAttribute("bufu", new BUFU());
        model.addAttribute("list_ibp", usuarioService.getAllIbps());
        model.addAttribute("http_method", "post");

        return "/legales/configuracion/bufu";
    }

    @PostMapping
    public String postBufus(@Valid @ModelAttribute("bufu") BUFU bufu, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            model.addAttribute("list_bufu", service.getAll());
            model.addAttribute("bufu", bufu);
            model.addAttribute("list_ibp", usuarioService.getAllIbps());
            model.addAttribute("http_method", "post");
            return "/legales/configuracion/bufu";
        }
        boolean alreadyExists = service.create(bufu);

        if(alreadyExists){
            return "redirect:/legales/configuracion/bufu-ibp?error&bufuExists";
        }

        return "redirect:/legales/configuracion/bufu-ibp";
    }

    @PutMapping("/{id}/add-ibp")
    public String addIbpABufu(@ModelAttribute("ibp") Usuario ibp, @PathVariable(name = "id") Long id,ModelMap model){
        if(ibp.getId() != null){
            BUFU bufu = service.getOne(id);
            bufu.addIbp(usuarioService.getOne(ibp.getId()));
            service.update(bufu);
        }
        return "redirect:/legales/configuracion/bufu-ibp/" + id;
    }

    @GetMapping("/{id}")
    public String getBufusEdit(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("list_bufu", service.getAll());
        model.addAttribute("bufu", service.getOne(id));
        model.addAttribute("ibp", new Usuario());
        model.addAttribute("list_ibp", usuarioService.getAllIbps());
        model.addAttribute("http_method", "put");

        return "/legales/configuracion/bufu";
    }

    @PutMapping()
    public String putBufus(Model model, @ModelAttribute("bufu") BUFU bufu){
        boolean alreadyExists = service.update(bufu);

        if(alreadyExists){
            return "redirect:/legales/configuracion/bufu-ibp?error&bufuExists";
        }

        return "redirect:/legales/configuracion/bufu-ibp";
    }

    @GetMapping("/{id}/delete-ibp/{idIbp}")
    public String getBufusEdit(Model model, @PathVariable(name = "id") Long id, @PathVariable(name = "idIbp") Long idIbp){
        BUFU bufu = service.getOne(id);
        bufu.removeIbp(usuarioService.getOne(idIbp));
        service.update(bufu);

        return "redirect:/legales/configuracion/bufu-ibp/" + id;
    }
}
