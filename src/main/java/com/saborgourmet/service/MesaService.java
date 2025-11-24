
package com.saborgourmet.service;

import com.saborgourmet.model.Mesa;
import com.saborgourmet.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Mesa guardarMesa(Mesa mesa) {
        // Control de capacidad de mesas
        if (mesa.getCapacidad() <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        // Control de mesas duplicadas
        Mesa existente = mesaRepository.findByNumero(mesa.getNumero());
        if (existente != null && (mesa.getId() == null || !existente.getId().equals(mesa.getId()))) {
            throw new IllegalArgumentException("Ya existe una mesa con ese número");
        }
        return mesaRepository.save(mesa);
    }

    public void eliminarMesa(Long id) {
        mesaRepository.deleteById(id);
    }

    public Mesa obtenerMesaPorId(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }
}
