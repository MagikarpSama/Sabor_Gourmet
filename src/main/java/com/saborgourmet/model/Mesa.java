package com.saborgourmet.model;

import javax.persistence.*;

@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private int capacidad;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}
