package com.example.demo3;

import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.ClienteYaExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMgr {

    @Autowired
    private ClienteRepository clienteRepository;

    public void addClient(Long document, String name, String email) throws ClienteYaExiste, InformacionInvalida {
        if (document == 0 || name.equals("") || email.equals("")) {
            throw new InformacionInvalida();
        }
        if (clienteRepository.findClienteByDocument(document) != null) {
            throw new ClienteYaExiste();
        }

        Cliente cliente = new Cliente(name, document, email);
        clienteRepository.save(cliente);
    }

}
