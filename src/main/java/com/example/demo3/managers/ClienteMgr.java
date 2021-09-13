package com.example.demo3.managers;

import com.example.demo3.entities.Cliente;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.ClienteYaExiste;
import com.example.demo3.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteMgr {

    @Autowired
    private ClienteRepository clienteRepository;

    public void addClient(Long document, String name, String email) throws ClienteYaExiste, InformacionInvalida {
        if (document == 0 || name.equals("") || email.equals("")) {
            throw new InformacionInvalida();
        }

        List<Cliente> lista = clienteRepository.findAllByDocument(document);
        if (lista != null && lista.size() > 0) {
            throw new ClienteYaExiste();
        }

        Cliente cliente = new Cliente(document, name, email);
        clienteRepository.save(cliente);
    }

}
