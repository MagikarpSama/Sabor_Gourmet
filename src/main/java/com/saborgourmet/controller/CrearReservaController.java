package com.saborgourmet.controller;

import com.saborgourmet.model.Reserva;
import com.saborgourmet.model.Cliente;
import com.saborgourmet.repository.ClienteRepository;
import com.saborgourmet.repository.MesaRepository;
import com.saborgourmet.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

@Controller
public class CrearReservaController {
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping("/crear-reserva")
    public String mostrarFormularioReserva(Model model, HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("mesas", mesaRepository.findAll());
        return "crear_reserva";
    }

    @PostMapping("/crear-reserva")
    public String crearReserva(@ModelAttribute Reserva reserva, Model model, HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        // Crear y guardar cliente con los datos del formulario
        Cliente cliente = reserva.getCliente();
        if (cliente != null) {
            clienteRepository.save(cliente);
        }
        reserva.setEstado("Reservada");
        try {
            reservaService.guardarReserva(reserva);
            // Mostrar carta de confirmación
            model.addAttribute("reserva", reserva);
            // Determinar si es admin
            boolean esAdmin = false;
            if (usuario instanceof com.saborgourmet.model.Usuario) {
                esAdmin = "ADMIN".equalsIgnoreCase(((com.saborgourmet.model.Usuario) usuario).getRol());
            }
            model.addAttribute("esAdmin", esAdmin);
            return "confirmacion_reserva";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("mesas", mesaRepository.findAll());
            model.addAttribute("reserva", reserva);
            return "crear_reserva";
        }
    }
}
