
package com.saborgourmet;

import com.saborgourmet.model.Usuario;
import com.saborgourmet.repository.UsuarioRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.saborgourmet.model.Mesa;
import com.saborgourmet.repository.MesaRepository;

@SpringBootApplication
public class SaborGourmetApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaborGourmetApplication.class, args);
    }

    @Bean
    public CommandLineRunner initMesasYAdmin(MesaRepository mesaRepository, UsuarioRepository usuarioRepository) {
        return args -> {
            // Mesas por defecto
            if (mesaRepository.count() == 0) {
                for (int i = 1; i <= 6; i++) {
                    Mesa mesa = new Mesa();
                    mesa.setNumero(i);
                    mesa.setCapacidad(4);
                    mesaRepository.save(mesa);
                }
            }
            // Usuario admin por defecto
            if (usuarioRepository.findByUsername("admin@admin.local.cl") == null) {
                Usuario admin = new Usuario();
                admin.setUsername("admin@admin.local.cl");
                admin.setPassword("12345");
                admin.setNombre("Administrador");
                admin.setRol("ADMIN");
                usuarioRepository.save(admin);
            }
        };
    }
}
