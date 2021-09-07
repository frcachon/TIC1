package com.example.demo3.persistence;

import com.example.demo3.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {

    List<Cliente> findAllByDocument(Long document);

}