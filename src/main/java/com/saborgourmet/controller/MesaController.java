

package com.saborgourmet.controller;
import com.saborgourmet.model.Mesa;
import com.saborgourmet.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @GetMapping("/mesas")
    public String listarMesas(Model model, @RequestParam(value = "success", required = false) String success, javax.servlet.http.HttpSession session) {
        Object usuarioObj = session.getAttribute("usuario");
        if (usuarioObj == null) {
            return "redirect:/login";
        }
        com.saborgourmet.model.Usuario usuario = (com.saborgourmet.model.Usuario) usuarioObj;
        if (!"USER".equals(usuario.getRol())) {
            return "redirect:/";
        }
        List<Mesa> mesas = mesaService.listarMesas();
        model.addAttribute("mesas", mesas);
        return "mesas";
    }


    @GetMapping("/mesas/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        Mesa mesa = mesaService.obtenerMesaPorId(id);
        if (mesa == null) return "redirect:/mesas";
        model.addAttribute("mesa", mesa);
        model.addAttribute("mesas", mesaService.listarMesas());
        return "mesas";
    }

    @PostMapping("/mesas/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Mesa mesa) {
        mesa.setId(id);
        mesaService.guardarMesa(mesa);
        return "redirect:/mesas";
    }

    @PostMapping("/mesas/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id) {
        mesaService.eliminarMesa(id);
        return "redirect:/mesas";
    }
}
