package com.saborgourmet.controller;

import com.saborgourmet.model.Mesa;
import com.saborgourmet.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

@Controller
public class CrearMesaController {
    @Autowired
    private MesaService mesaService;

    @GetMapping("/mesas/crear")
    public String mostrarCrearMesa(HttpSession session, Model model) {
        Object usuarioObj = session.getAttribute("usuario");
        if (usuarioObj == null) {
            return "redirect:/login";
        }
        com.saborgourmet.model.Usuario usuario = (com.saborgourmet.model.Usuario) usuarioObj;
        if (!"ADMIN".equals(usuario.getRol())) {
            return "redirect:/";
        }
        model.addAttribute("mesa", new Mesa());
        return "crear_mesa";
    }

    @PostMapping("/mesas/nueva")
    public String crearMesa(@ModelAttribute Mesa mesa, Model model, HttpSession session) {
        Object usuarioObj = session.getAttribute("usuario");
        if (usuarioObj == null) {
            return "redirect:/login";
        }
        com.saborgourmet.model.Usuario usuario = (com.saborgourmet.model.Usuario) usuarioObj;
        if (!"ADMIN".equals(usuario.getRol())) {
            return "redirect:/";
        }
        try {
            mesaService.guardarMesa(mesa);
            model.addAttribute("success", "Mesa creada correctamente");
            model.addAttribute("mesa", new Mesa());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("mesa", mesa);
        }
        return "crear_mesa";
    }
}
