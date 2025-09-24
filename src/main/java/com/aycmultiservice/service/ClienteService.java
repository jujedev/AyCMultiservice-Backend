package com.aycmultiservice.service;

import com.aycmultiservice.dto.ClienteDTO;
import com.aycmultiservice.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteDTO crearCliente(Cliente cliente);
    List<ClienteDTO> listarClientes();
    Optional<ClienteDTO> obtenerClientePorId(Long id);
    Optional<ClienteDTO> obtenerClientePorDni(String dni);
    ClienteDTO actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}
