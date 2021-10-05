package com.example.demo3.entities;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Operador {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // "id" es el id de la tabla. "identificador" es el id del operador
    private Integer id;

    @Column(name="empresa")
    private String empresa;

    @Column(name = "departamento")
    private String departamento;

    @Column(name="telefono")
    private Long telefono;

    @Column(name="emailcontacto")
    private String emailcontacto;

    @Column(name="direccion")
    private String direccion;

    public Operador() {
    }

    public Operador(String nombre_empresa, String departamento, Long telefono,
                    String email_contacto, String direccion) {
        this.empresa = nombre_empresa;
        this.departamento = departamento;
        this.telefono = telefono;
        this.emailcontacto = email_contacto;
        this.direccion = direccion;
    }

    public String getNombre_empresa() {
        return empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.empresa = nombre_empresa;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getEmail_contacto() {
        return emailcontacto;
    }

    public void setEmail_contacto(String email_contacto) {
        this.emailcontacto = email_contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Operador{" +
                "id=" + id +
                ", nombre_empresa='" + empresa + '\'' +
                ", departamento='" + departamento + '\'' +
                ", telefono=" + telefono +
                ", email_contacto='" + emailcontacto + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}