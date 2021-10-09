package com.example.demo3.managers;

import com.example.demo3.entities.Admin;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Empleado;
import com.example.demo3.entities.Operador;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.persistence.AdminRepository;
import com.example.demo3.persistence.ClienteRepository;
import com.example.demo3.persistence.EmpleadoRepository;
import com.example.demo3.persistence.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoMgr {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdminRepository adminRepository;

    public void addEmpleado(String username, String password, String empresa) throws NombreDeUsuarioYaExiste {
        Operador op = operadorRepository.findOperadorByEmpresa(empresa);

        List<Cliente> client_username_check = clienteRepository.findAllByMail(username);
        if (client_username_check != null && client_username_check.size() > 0) {
            throw new NombreDeUsuarioYaExiste();
        }// chequeo que ese nombre de usuario no este asociado a ningun cliente

        List<Empleado> emp_username_check = empleadoRepository.findAllByUsername(username);
        if (emp_username_check != null && emp_username_check.size() > 0) {
            throw new NombreDeUsuarioYaExiste();
        }// chequeo que ese nombre de usuario no este asociado a ningun empleado

        List<Admin> admin_username_check = adminRepository.findAllByMail(username);
        if (admin_username_check != null && admin_username_check.size() > 0) {
            throw new NombreDeUsuarioYaExiste();
        }// chequeo que ese nombre de usuario no este asociado a ningun admin

        Empleado empleado = new Empleado(username, password, op.getId());
        empleadoRepository.save(empleado);

    }

}
