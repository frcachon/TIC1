package com.example.demo3.persistence;

import com.example.demo3.entities.Operador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OperadorRepository extends CrudRepository<Operador,Integer> {

    List<Operador> findAllByIdentificador(Long id);

}