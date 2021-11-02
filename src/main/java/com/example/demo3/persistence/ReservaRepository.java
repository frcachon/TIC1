package com.example.demo3.persistence;

import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Reserva;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

    List<Reserva> findAllByIdCliente(Integer id_cliente);

}