package com.example.demo3;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {

    Cliente findClienteByDocument(Long document);

}
