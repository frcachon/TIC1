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

    /*public void addOperador(Long identificador, String name, String email) throws OperadorYaExiste, InformacionInvalida {
        if (identificador == 0 || name.equals("") || email.equals("")) {
            throw new InformacionInvalida();
        }

        List<Operador> lista = operadorRepository.findAllByIdentificador(identificador);
        if (lista != null && lista.size() > 0) {
            throw new OperadorYaExiste();
        }

        //Operador operador = new Operador(identificador, name, email);
        //operadorRepository.save(operador);
    }*/

    public void addOperador(String nombreEmpresa, String nombreEmpleado, String contrasena, String departamento,
                            Long telefono, String emailContacto, String direccion) throws InformacionInvalida, OperadorYaExiste {

        if (nombreEmpresa.equals("") || nombreEmpleado.equals("") || contrasena.equals("") || departamento.equals("") ||
                telefono == 0 || emailContacto.equals("") || direccion.equals("")) {
            throw new InformacionInvalida();
        }

        List<Operador> lista = operadorRepository.findAllByempresa(nombreEmpresa);
        if (lista != null && lista.size() > 0) {
            throw new OperadorYaExiste();
        }

        Operador operador = new Operador(nombreEmpresa, nombreEmpleado, contrasena, departamento,
                telefono, emailContacto,direccion);

        operadorRepository.save(operador);
    }
}