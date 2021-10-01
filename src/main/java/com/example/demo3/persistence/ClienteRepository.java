package com.example.demo3.persistence;

import com.example.demo3.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Integer> {

    List<Cliente> findAllByUsername(String username);
    List<Cliente> findAllByDocumentoAndAndPais(Long documento, String pais);


}