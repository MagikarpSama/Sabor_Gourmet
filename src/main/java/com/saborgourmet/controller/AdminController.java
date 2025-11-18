package com.saborgourmet.controller;

import com.saborgourmet.model.Usuario;
import com.saborgourmet.model.Mesa;
import com.saborgourmet.repository.UsuarioRepository;
import com.saborgourmet.repository.MesaRepository;
import com.saborgourmet.repository.ClienteRepository;
import com.saborgourmet.model.Cliente;
import com.saborgourmet.service.ReservaService;
import com.saborgourmet.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ReservaService reservaService;

    @GetMapping("/administracion")
    public String administracion(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        if (!"ADMIN".equals(usuario.getRol())) {
            return "redirect:/";
        }
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Mesa> mesas = mesaRepository.findAll();
        List<Reserva> reservas = reservaService.listarReservas();
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("clientes", clientes);
        model.addAttribute("mesas", mesas);
        model.addAttribute("reservas", reservas);
        return "administracion";
    }
}
