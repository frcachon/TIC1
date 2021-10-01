package com.example.demo3.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Interes {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // "id" es el id de la tabla
    private Integer id_interes;

    @Column(name="interes")
    private String interes;

    public Interes() {
    }

    public Interes(String interes) {
        this.interes = interes;
    }

    public Integer getId_interes() {
        return id_interes;
    }

    public void setId_interes(Integer id_interes) {
        this.id_interes = id_interes;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    @Override
    public String toString() {
        return interes;
    }
}