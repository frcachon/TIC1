package com.example.demo3.managers;

import com.example.demo3.entities.Cliente;
import com.example.demo3.exceptions.DocumentoYaExisteParaMismoPais;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteMgr {

    @Autowired
    private ClienteRepository clienteRepository;

    public void addClient(String username, String mail, String contrasena, Long documento, String tipo_documento, LocalDate fecha_nacimiento, Boolean vacuna_covid, String pais) throws NombreDeUsuarioYaExiste, InformacionInvalida, DocumentoYaExisteParaMismoPais {
        //if (document == 0 || name.equals("") || email.equals("")) {
        //    throw new InformacionInvalida();
        //}

        List<Cliente> username_check = clienteRepository.findAllByUsername(username);
        if (username_check != null && username_check.size() > 0) {
            throw new NombreDeUsuarioYaExiste();
        }

        List<Cliente> doc_check = clienteRepository.findAllByDocumentoAndAndPais(documento, pais);
        if (doc_check != null && doc_check.size() > 0) {
            throw new DocumentoYaExisteParaMismoPais();
        }

        Cliente cliente = new Cliente(username, mail, contrasena, documento, tipo_documento, fecha_nacimiento, vacuna_covid, pais);
        clienteRepository.save(cliente);
    }

}
