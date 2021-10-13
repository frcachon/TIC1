package com.example.demo3.entities;

import javafx.scene.image.Image;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="titulo")
    private String titulo;

    @Lob
    @Column(name="imagen_actividad", columnDefinition = "BLOB")
    private byte[] imagen_actividad;

    @Column(name="id_operador")
    private Integer id_operador;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="horario_apertura")
    private LocalTime apertura;

    @Column(name="horario_cierre")
    private LocalTime cierre;

    @Column(name="validada")
    private Boolean validada;

    @Column(name="cupo")
    private Integer cupo;

    @Column(name="utiliza_reservas")
    private Boolean utiliza_reservas;



    public Actividad(){}

    public Actividad(String titulo, byte[] imagen_actividad, Integer id_operador, String descripcion, LocalTime apertura,
                     LocalTime cierre, Boolean validada, Integer cupo, Boolean utiliza_reservas) {
        this.titulo = titulo;
        this.imagen_actividad = imagen_actividad;
        this.id_operador = id_operador;
        this.descripcion = descripcion;
        this.apertura = apertura;
        this.cierre = cierre;
        this.validada = validada;
        this.cupo = cupo;
        this.utiliza_reservas = utiliza_reservas;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte[] getImagen_actividad() {
        return imagen_actividad;
    }
    public void setImagen_actividad(byte[] imagen_actividad) { this.imagen_actividad = imagen_actividad; }

    public Integer getId_operador() {
        return id_operador;
    }
    public void setId_operador(Integer id_operador) {
        this.id_operador = id_operador;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getValidada() {
        return validada;
    }
    public void setValidada(Boolean validada) {
        this.validada = validada;
    }

    public Integer getCupo() {
        return cupo;
    }
    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Boolean getUtiliza_reservas() {
        return utiliza_reservas;
    }
    public void setUtiliza_reservas(Boolean utiliza_reservas) {
        this.utiliza_reservas = utiliza_reservas;
    }

    public LocalTime getApertura() {
        return apertura;
    }

    public void setApertura(LocalTime apertura) {
        this.apertura = apertura;
    }

    public LocalTime getCierre() {
        return cierre;
    }

    public void setCierre(LocalTime cierre) {
        this.cierre = cierre;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                "imagen=" + imagen_actividad + '\'' +
                ", id_operador=" + id_operador +
                ", descripcion='" + descripcion + '\'' +
                ", apertura=" + apertura +
                ", cierre=" + cierre +
                ", validada=" + validada +
                ", cupo=" + cupo +
                ", utiliza_reservas=" + utiliza_reservas +
                '}';
    }

}
