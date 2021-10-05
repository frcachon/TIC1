package com.example.demo3.entities;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="actividad")
    private String actividad;

    @Column(name="id_operador")
    private Integer id_operador;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="horario")
    private LocalTime horario;

    @Column(name="validada")
    private Boolean validada;

    @Column(name="cupo")
    private Integer cupo;

    @Column(name="utiliza_reservas")
    private Boolean utiliza_reservas;

    public Actividad(){}

    public Actividad(Integer id, String actividad, Integer id_operador, String descripcion, LocalTime horario,
                     Boolean validada, Integer cupo, Boolean utiliza_reservas) {
        this.id = id;
        this.actividad = actividad;
        this.id_operador = id_operador;
        this.descripcion = descripcion;
        this.horario = horario;
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

    public String getActividad() {
        return actividad;
    }
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

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

    public LocalTime getHorario() {
        return horario;
    }
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", actividad='" + actividad + '\'' +
                ", id_operador=" + id_operador +
                ", descripcion='" + descripcion + '\'' +
                ", horario=" + horario +
                ", validada=" + validada +
                ", cupo=" + cupo +
                ", utiliza_reservas=" + utiliza_reservas +
                '}';
    }
}
