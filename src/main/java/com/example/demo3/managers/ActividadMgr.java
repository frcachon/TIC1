package com.example.demo3.managers;

import com.example.demo3.entities.Actividad;
import com.example.demo3.persistence.ActividadRepository;
import com.example.demo3.persistence.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadMgr {

    @Autowired
    OperadorRepository operadorRepository;

    @Autowired
    ActividadRepository actividadRepository;

    public void addActividad() {

    }

    public void validarActividad(Actividad actividad) {
        actividad.setValidada(true);
        actividadRepository.save(actividad);
    }

    public void rechazarActividad(Actividad actividad) {
        actividadRepository.delete(actividad);
    }

}
