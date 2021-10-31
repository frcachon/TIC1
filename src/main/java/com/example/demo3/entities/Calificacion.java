package com.example.demo3.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="idcliente")
    private Integer id_cliente;

    @Column(name="idactividad")
    private Integer id_actividad;

    @Column(name="puntos")
    private Integer puntos;

    public Calificacion() {
    }

    public Calificacion(Integer id_cliente, Integer id_actividad, Integer puntos) {
        this.id_cliente = id_cliente;
        this.id_actividad = id_actividad;
        this.puntos = puntos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(Integer id_actividad) {
        this.id_actividad = id_actividad;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Calificacion{" +
                "id=" + id +
                ", id_cliente=" + id_cliente +
                ", id_actividad=" + id_actividad +
                ", puntos=" + puntos +
                '}';
    }

}