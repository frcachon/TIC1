package com.example.demo3.managers;

import com.example.demo3.entities.Operador;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.OperadorYaExiste;
import com.example.demo3.persistence.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadorMgr {

    @Autowired
    private OperadorRepository operadorRepository;

    public void addOperador(Long identificador, String name, String email) throws OperadorYaExiste, InformacionInvalida {
        if (identificador == 0 || name.equals("") || email.equals("")) {
            throw new InformacionInvalida();
        }

        List<Operador> lista = operadorRepository.findAllByIdentificador(identificador);
        if (lista != null && lista.size() > 0) {
            throw new OperadorYaExiste();
        }

        Operador operador = new Operador(identificador, name, email);
        operadorRepository.save(operador);
    }

}