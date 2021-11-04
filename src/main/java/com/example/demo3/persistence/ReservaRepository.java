package com.example.demo3.persistence;

import com.example.demo3.entities.Reserva;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.time.LocalDate;

public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

    List<Reserva> findAllByIdCliente(Integer id_cliente);
    List<Reserva> findAllByIdactividadAndFecha(Integer id_actividad, LocalDate fecha);
    List<Reserva> findAllByIdactividad(Integer id_actividad);

}