package com.aycmultiservice.service.impl;

import com.aycmultiservice.dto.ClienteDTO;
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
    public ClienteDTO crearCliente(Cliente cliente){
        Cliente saved = clienteRepository.save(cliente);
        return mapToDTO(saved);
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public Optional<ClienteDTO> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public Optional<ClienteDTO> obtenerClientePorDni(String dni) {
        return clienteRepository.findByDni(dni)
                .map(this::mapToDTO);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(c -> {
                    c.setNombre(cliente.getNombre());
                    c.setApellido(cliente.getApellido());
                    c.setDni(cliente.getDni());
                    c.setEmail(cliente.getEmail());
                    c.setTelefono(cliente.getTelefono());
                    Cliente updated = clienteRepository.save(c);
                    return mapToDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO mapToDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getEmail(),
                cliente.getTelefono()
        );
    }
}
