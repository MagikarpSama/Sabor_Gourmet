
package com.saborgourmet.controller;

import com.saborgourmet.model.Reserva;
import com.saborgourmet.repository.ClienteRepository;
import com.saborgourmet.repository.MesaRepository;
import com.saborgourmet.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class ReservaController {
    // Redirigir GET /reservas/nueva a /reservas para evitar error 400
    @GetMapping("/reservas/nueva")
    public String redirigirNuevaReserva() {
        return "redirect:/reservas";
    }

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping("/reservas")
    public String listarReservas(Model model, @RequestParam(value = "success", required = false) String success, javax.servlet.http.HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        List<Reserva> reservas = reservaService.listarReservas();
        model.addAttribute("reservas", reservas);
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("mesas", mesaRepository.findAll());
        if (success != null) {
            model.addAttribute("success", "Reserva creada correctamente");
        }
        return "reservas";
    }

    @PostMapping("/reservas/nueva")
    public String crearReserva(@ModelAttribute Reserva reserva, Model model) {
        reserva.setEstado("Reservada");
        try {
            reservaService.guardarReserva(reserva);
            return "redirect:/reservas?success=1";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("reservas", reservaService.listarReservas());
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("mesas", mesaRepository.findAll());
            model.addAttribute("reserva", reserva);
            return "reservas";
        }
    }

    @PostMapping("/reservas/editar/{id}")
    public String guardarEdicionReserva(@PathVariable Long id, @ModelAttribute Reserva reserva) {
        reserva.setId(id);
        reservaService.guardarReserva(reserva);
        return "redirect:/reservas";
    }

    @PostMapping("/reservas/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva != null) {
            reserva.setEstado("Cancelada");
            reservaService.guardarReserva(reserva);
        }
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva == null) {
            return "redirect:/reservas";
        }
        model.addAttribute("reserva", reserva);
        model.addAttribute("reservas", reservaService.listarReservas());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("mesas", mesaRepository.findAll());
        return "reservas";
    }

    @PostMapping("/reservas/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return "redirect:/reservas";
    }

    @PostMapping("/reservas/confirmar/{id}")
    public String confirmarReserva(@PathVariable Long id) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva != null && ("Reservada".equalsIgnoreCase(reserva.getEstado()))) {
            reserva.setEstado("Confirmada");
            reservaService.guardarReserva(reserva);
        }
        return "redirect:/reservas";
    }

    @PostMapping("/reservas/liberar/{id}")
    public String liberarReserva(@PathVariable Long id) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva != null && ("Confirmada".equalsIgnoreCase(reserva.getEstado()))) {
            reserva.setEstado("Reservada");
            reservaService.guardarReserva(reserva);
        }
        return "redirect:/reservas";
    }
}
