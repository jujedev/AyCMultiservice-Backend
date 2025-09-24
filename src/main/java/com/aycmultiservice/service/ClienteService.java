package com.aycmultiservice.service;

import com.aycmultiservice.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Optional<Cliente> obtenerClientePorId(Long id);
    Optional<Cliente> obtenerClientePorDni(String dni);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}
