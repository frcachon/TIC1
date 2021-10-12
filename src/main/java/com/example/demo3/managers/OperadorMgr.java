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

    Operador operador;
    public void setOperador(Operador operador) {
        this.operador = operador;
    }

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

    public void addOperador(String nombreEmpresa, String departamento,
                            Long telefono, String emailContacto, String direccion) throws InformacionInvalida, OperadorYaExiste {

        if (nombreEmpresa.equals("") || departamento.equals("") ||
                telefono == 0 || emailContacto.equals("") || direccion.equals("")) {
            throw new InformacionInvalida();
        }

        List<Operador> lista = operadorRepository.findAllByempresa(nombreEmpresa);
        if (lista != null && lista.size() > 0) {
            throw new OperadorYaExiste();
        }

        Operador operador = new Operador(nombreEmpresa, departamento,
                telefono, emailContacto, direccion);

        operadorRepository.save(operador);
    }

    public void bloquearOperador(Operador operador) {
        operador.setBloqueado(true);
        operadorRepository.save(operador);
    }

    public void desbloquearOperador(Operador operador) {
        operador.setBloqueado(false);
        operadorRepository.save(operador);
    }

    public void updateOperador (String nombreEmpresa, String departamento, Long telefono,
                                String emailContacto, String direccion) {
    if (nombreEmpresa != null) {
        if (!nombreEmpresa.equals("")) {
            operador.setEmpresa(nombreEmpresa);
        }
        }
    if (departamento != null) {
        operador.setDepartamento(departamento);
    }
    if (telefono != 0L) {
        operador.setTelefono(telefono);
    }
    if (emailContacto != null) {
        if (!emailContacto.equals("")) {
            operador.setEmailcontacto(emailContacto);
        }
    }
    if (direccion != null) {
        if (!direccion.equals("")) {
            operador.setDireccion(direccion);
        }
    }
    operadorRepository.save(operador);
    }

}