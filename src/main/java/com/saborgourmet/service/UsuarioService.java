package com.saborgourmet.service;

import com.saborgourmet.model.Usuario;
import com.saborgourmet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        // Creacion de rol de usuario
        if (usuario.getUsername() != null && usuario.getUsername().equalsIgnoreCase("admin@admin.local.cl")) {
            usuario.setRol("ADMIN");
        } else {
            usuario.setRol("USER");
        }
        
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
