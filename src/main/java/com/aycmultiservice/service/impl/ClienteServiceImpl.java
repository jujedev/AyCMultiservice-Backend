package com.aycmultiservice.service.impl;

import com.aycmultiservice.model.Cliente;
import com.aycmultiservice.repository.ClienteRepository;
import com.aycmultiservice.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> obtenerClientePorDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(c -> {
                    c.setNombre(cliente.getNombre());
                    c.setApellido(cliente.getApellido());
                    c.setDni(cliente.getDni());
                    c.setEmail(cliente.getEmail());
                    c.setTelefono(cliente.getTelefono());
                    return clienteRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
