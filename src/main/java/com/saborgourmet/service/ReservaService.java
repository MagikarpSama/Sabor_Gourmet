
package com.saborgourmet.service;

import com.saborgourmet.model.Reserva;
import com.saborgourmet.model.Mesa;
import com.saborgourmet.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva guardarReserva(Reserva reserva) {
        // Control de cantidad de persoas
        if (reserva.getCantidadPersonas() <= 0) {
            throw new IllegalArgumentException("La cantidad de personas debe ser mayor a 0");
        }
        // control de fecha y hora
        if (reserva.getFechaHora() != null && reserva.getFechaHora().isBefore(java.time.LocalDateTime.now().plusDays(1))) {
            throw new IllegalArgumentException("La reserva debe hacerse con al menos un día de anticipación");
        }
        // Validar que no exista una reserva activa 
        if (reserva.getMesa() != null && reserva.getFechaHora() != null) {
            boolean existeConflicto = reservaRepository.findAll().stream()
                .anyMatch(r -> r.getId() != reserva.getId() &&
                    r.getMesa().getId().equals(reserva.getMesa().getId()) &&
                    r.getFechaHora().equals(reserva.getFechaHora()) &&
                    !"Cancelada".equalsIgnoreCase(r.getEstado()));
            if (existeConflicto) {
                throw new IllegalArgumentException("Ya existe una reserva para esa mesa y hora.");
            }
        }
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }
}
