package com.saborgourmet.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaHora;
    private int cantidadPersonas;
    private String estado; // Reservada, Cancelada, etc.

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Mesa getMesa() { return mesa; }
    public void setMesa(Mesa mesa) { this.mesa = mesa; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public int getCantidadPersonas() { return cantidadPersonas; }
    public void setCantidadPersonas(int cantidadPersonas) { this.cantidadPersonas = cantidadPersonas; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
