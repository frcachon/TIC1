package com.example.demo3.managers;

import com.example.demo3.entities.Actividad;

import com.example.demo3.persistence.ActividadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalTime;

@Service
public class ActividadMgr {

    @Autowired
    private ActividadRepository actividadRepository;

    public void registrarActividad(String titulo, byte[] imagen_actividad, Integer id_operador, String descripcion, LocalTime apertura,
                                          LocalTime cierre, Boolean validada, Integer cupo, Boolean utiliza_reservas)  {


        Actividad actividad = new Actividad(titulo, imagen_actividad, id_operador, descripcion, apertura,
                cierre, validada, cupo, utiliza_reservas);
        actividad.setValidada(false); //al inicio no esta validada
        actividadRepository.save(actividad);
    }

}
