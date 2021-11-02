package com.example.demo3.managers;

import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Reserva;
import com.example.demo3.persistence.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaMgr {

    @Autowired
    private ReservaRepository reservaRepository;

    public void crearReserva(Integer id_cliente, Integer id_actividad, LocalDate fecha, LocalTime hora, Integer cantidad) {
        Reserva res = new Reserva(id_cliente, id_actividad, fecha, hora, cantidad);
        // chequeos
        reservaRepository.save(res);
    }

    public List<Reserva> getReservasFromCliente(Integer id_cliente) {
        return  (List<Reserva>) reservaRepository.findAllByIdCliente(id_cliente);
    }



}