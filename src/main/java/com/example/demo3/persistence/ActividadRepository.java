package com.example.demo3.persistence;

import com.example.demo3.entities.Actividad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends CrudRepository<Actividad,Integer> {

}
