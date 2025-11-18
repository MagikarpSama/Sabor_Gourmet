package com.saborgourmet.controller;

import com.saborgourmet.model.Usuario;
import com.saborgourmet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AuthController {
        // Página de inicio: solo muestra iniciar sesión si no hay sesión activa
    @GetMapping("/")
    public String inicio(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "index";
        } else if ("ADMIN".equals(usuario.getRol())) {
            return "redirect:/administracion";
        } else if ("USER".equals(usuario.getRol())) {
            return "redirect:/mesas";
        } else {
            return "index";
        }
    }
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioService.buscarPorUsername(usuario.getUsername()) != null) {
            model.addAttribute("error", "El usuario ya existe");
            return "registro";
        }
        // El nombre ya viene del formulario y se guarda automáticamente
        usuarioService.registrarUsuario(usuario);
        model.addAttribute("mensaje", "Usuario registrado correctamente");
        return "login";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Usuario user = usuarioService.buscarPorUsername(usuario.getUsername());
        if (user != null && user.getPassword().equals(usuario.getPassword())) {
            session.setAttribute("usuario", user);
            if ("ADMIN".equals(user.getRol())) {
                return "redirect:/administracion";
            } else if ("USER".equals(user.getRol())) {
                return "redirect:/mesas";
            } else {
                return "index";
            }
        }
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

    @Autowired
    private com.saborgourmet.service.MesaService mesaService;

    // Eliminado: panel de usuario, ahora todo es administracion

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
