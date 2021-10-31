package com.example.demo3.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="idcliente")
    private Integer id_cliente;

    @Column(name="idactividad")
    private Integer id_actividad;

    @Column(name="fecha")
    private LocalDate fecha;

    @Column(name="comentario")
    private String comentario;

    public Comentario() {
    }

    public Comentario(Integer id_cliente, Integer id_actividad, String comentario) {
        this.id_cliente = id_cliente;
        this.id_actividad = id_actividad;
        this.fecha = LocalDate.now();
        this.comentario = comentario;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", id_cliente=" + id_cliente +
                ", id_actividad=" + id_actividad +
                ", fecha=" + fecha +
                ", comentario='" + comentario + '\'' +
                '}';
    }

}