package com.example.demo3.managers;

import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Reserva;
import com.example.demo3.persistence.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ActividadMgr {

    Actividad actividad;

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Autowired
    ActividadRepository actividadRepository;

    public void validarActividad(Actividad actividad) {
        actividad.setValidada(true);
        actividadRepository.save(actividad);
    }

    public void rechazarActividad(Actividad actividad) {
        actividadRepository.delete(actividad);
    }

    public void registrarActividad(String titulo, byte[] imagen_actividad, Integer idoperador, String descripcion, LocalTime apertura,
                                          LocalTime cierre, Boolean validada, Integer cupo, Boolean utiliza_reservas)  {

        Actividad actividad = new Actividad(titulo, imagen_actividad, idoperador, descripcion, apertura,
                cierre, validada, cupo, utiliza_reservas);
        actividad.setValidada(false); //al inicio no esta validada
        actividadRepository.save(actividad);
    }

    public List<Actividad> getActividadesNoValidadas() {
        return  (List<Actividad>) actividadRepository.findAllByValidadaIsFalse();
    }

    public List<Actividad> getActividadesFromOperador(Integer id_operador) {
        return  (List<Actividad>) actividadRepository.findAllByIdoperador(id_operador);
    }

    public List<Actividad> getAll() {
        return  (List<Actividad>) actividadRepository.findAll();
    }

    public List<Actividad> getActividadesFromTituloContaining(String str) {
        return (List<Actividad>) actividadRepository.findAllByTituloContaining(str);
    }

    public void updateActividad(String titulo, String descripcion, LocalTime apertura, LocalTime cierre,
                                Integer cupo, Boolean utiliza_reservas, byte[] imagen) {

        if (titulo != null) {
            if (!titulo.equals("")) {
                actividad.setTitulo(titulo);
            }
        }

        if (descripcion != null) {
            if (!descripcion.equals("")) {
                actividad.setDescripcion(descripcion);
            }
        }

        if (apertura != null) {
            actividad.setApertura(apertura);
        }

        if (cierre != null) {
            actividad.setCierre(cierre);
        }

        if (cupo != 0) {
            actividad.setCupo(cupo);
        }

        if (cierre != null) {
            actividad.setImagenactividad(imagen);
        }

        actividad.setUtiliza_reservas(utiliza_reservas);
        actividad.setValidada(false);
        actividadRepository.save(actividad);
    }

    public void eliminarActividad(Actividad actividad){
        actividadRepository.delete(actividad);
    }

}