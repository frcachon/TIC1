package com.example.demo3.entities;

import com.example.demo3.persistence.OperadorRepository;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Arrays;

@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="titulo")
    private String titulo;

    @Lob
    @Column(name="imagenactividad", columnDefinition = "BLOB")
    private byte[] imagenactividad;

    @Column(name="idoperador")
    private Integer idoperador;

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

    @Column(name="promediopuntuaciones")
    private Float promediopuntuaciones;

    @Column(name="cantidadpuntuaciones")
    private Integer cantidadpuntuaciones;

    public Actividad(){}

    public Actividad(String titulo, byte[] imagenactividad, Integer idoperador, String descripcion, LocalTime apertura,
                     LocalTime cierre, Boolean validada, Integer cupo, Boolean utiliza_reservas) {
        this.titulo = titulo;
        this.imagenactividad = imagenactividad;
        this.idoperador = idoperador;
        this.descripcion = descripcion;
        this.apertura = apertura;
        this.cierre = cierre;
        this.validada = validada;
        this.cupo = cupo;
        this.utiliza_reservas = utiliza_reservas;
        this.promediopuntuaciones = 0F;
        this.cantidadpuntuaciones = 0;
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

    public byte[] getImagenactividad() {
        return imagenactividad;
    }

    public void setImagenactividad(byte[] imagenactividad) {
        this.imagenactividad = imagenactividad;
    }

    public Integer getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(Integer idoperador) {
        this.idoperador = idoperador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Float getPromediopuntuaciones() {
        return promediopuntuaciones;
    }

    public void setPromediopuntuaciones(Float promediopuntuaciones) {
        this.promediopuntuaciones = promediopuntuaciones;
    }

    public Integer getCantidadpuntuaciones() {
        return cantidadpuntuaciones;
    }

    public void setCantidadpuntuaciones(Integer cantidadpuntuaciones) {
        this.cantidadpuntuaciones = cantidadpuntuaciones;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", imagenactividad=" + Arrays.toString(imagenactividad) +
                ", idoperador=" + idoperador +
                ", descripcion='" + descripcion + '\'' +
                ", apertura=" + apertura +
                ", cierre=" + cierre +
                ", validada=" + validada +
                ", cupo=" + cupo +
                ", utiliza_reservas=" + utiliza_reservas +
                ", promediopuntuaciones=" + promediopuntuaciones +
                ", cantidadpuntuaciones=" + cantidadpuntuaciones +
                '}';
    }

}