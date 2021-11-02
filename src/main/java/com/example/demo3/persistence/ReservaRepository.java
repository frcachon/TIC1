package com.example.demo3.persistence;

import com.example.demo3.entities.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva, Integer> {



}