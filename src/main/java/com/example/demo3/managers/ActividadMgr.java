package com.example.demo3.managers;

import com.example.demo3.entities.Actividad;
import com.example.demo3.persistence.ActividadRepository;
import com.example.demo3.persistence.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalTime;

@Service
public class ActividadMgr {

    @Autowired
    OperadorRepository operadorRepository;

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

}
