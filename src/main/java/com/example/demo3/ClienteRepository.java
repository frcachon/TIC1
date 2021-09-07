package com.example.demo3;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {

    List<Cliente> findAllByDocument(Long document);

}