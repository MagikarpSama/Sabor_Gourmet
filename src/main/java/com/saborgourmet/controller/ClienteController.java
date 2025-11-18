package com.saborgourmet.controller;

import com.saborgourmet.model.Cliente;
import com.saborgourmet.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public String listarClientes(Model model, @RequestParam(value = "success", required = false) String success, javax.servlet.http.HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", new Cliente());
        if (success != null) {
            model.addAttribute("success", "Cliente creado correctamente");
        }
        return "clientes";
    }

    @PostMapping("/clientes/nuevo")
    public String crearCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes?success=1";
    }

    @GetMapping("/clientes/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) return "redirect:/clientes";
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @PostMapping("/clientes/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        cliente.setId(id);
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}
