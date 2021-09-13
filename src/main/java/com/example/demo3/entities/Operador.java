package com.example.demo3.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Operador {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // "id" es el id de la tabla. "identificador" es el id del operador
    private Integer id;

    @Column(name="identificador")
    private Long identificador;

    @Column(name="nombre")
    private String name;

    @Column(name="email")
    private String email;

    public Operador(Long identificador, String name, String email) {
        this.identificador = identificador;
        this.name = name;
        this.email = email;
    }

    public Operador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long id) {
        this.identificador = identificador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Operador{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}