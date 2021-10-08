package com.example.demo3.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Interes {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // "id" es el id de la tabla
    private Integer id_interes;

    @Column(name="nombre")
    private String nombre;

    public Interes() {
    }

    public Interes(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_interes() {
        return id_interes;
    }

    public void setId_interes(Integer id_interes) {
        this.id_interes = id_interes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}