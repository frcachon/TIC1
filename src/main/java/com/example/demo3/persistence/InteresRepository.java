package com.example.demo3.persistence;

import com.example.demo3.entities.Interes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresRepository extends CrudRepository<Interes,Integer> {

    Interes findByNombre(String nombre);


}