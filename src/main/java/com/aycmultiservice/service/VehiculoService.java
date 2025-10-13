package com.aycmultiservice.service;

import com.aycmultiservice.dto.VehiculoDTO;
import com.aycmultiservice.dto.VehiculoRequestDTO;
import com.aycmultiservice.model.Cliente;
import com.aycmultiservice.model.Vehiculo;
import com.aycmultiservice.repository.ClienteRepository;
import com.aycmultiservice.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository, ClienteRepository clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    // Listar todos
    public List<VehiculoDTO> getAllVehiculos(){
        return vehiculoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener uno por ID
    public VehiculoDTO getVehiculoById(Long vehiculoId) {
        Vehiculo v = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + vehiculoId));
        return mapToDTO(v);
    }

    // Listar por cliente
    public List<VehiculoDTO> getVehiculosByCliente(Long clienteId){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));
        return cliente.getVehiculos().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Crear vehiculo
    public VehiculoDTO addVehiculoToCliente(Long clienteId, VehiculoRequestDTO vehiculoRequest){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatente(vehiculoRequest.getPatente());
        vehiculo.setMarca(vehiculoRequest.getMarca());
        vehiculo.setModelo(vehiculoRequest.getModelo());
        vehiculo.setAnio(vehiculoRequest.getAnio());
        vehiculo.setKilometros(vehiculoRequest.getKilometros());
        vehiculo.setCliente(cliente);

        Vehiculo saved = vehiculoRepository.save(vehiculo);
        return mapToDTO(saved);
    }

    // Actualizar vehiculo (y reasignar cliente si se cambia)
    public VehiculoDTO updateVehiculo(Long vehiculoId, VehiculoRequestDTO request) {
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + vehiculoId));

        vehiculo.setMarca(request.getMarca());
        vehiculo.setModelo(request.getModelo());
        vehiculo.setPatente(request.getPatente());
        vehiculo.setAnio(request.getAnio());
        vehiculo.setKilometros(request.getKilometros());

        // Reasignar cliente si llega un nuevo clienteId
        if (request.getClienteId() != null) {
            Cliente nuevoCliente = clienteRepository.findById(request.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + request.getClienteId()));
            vehiculo.setCliente(nuevoCliente);
        }

        vehiculoRepository.save(vehiculo);
        return mapToDTO(vehiculo);
    }

    // Eliminar vehiculo
    public void deleteVehiculo(Long vehiculoId) {
        if (!vehiculoRepository.existsById(vehiculoId)) {
            throw new RuntimeException("Vehículo no encontrado");
        }
        vehiculoRepository.deleteById(vehiculoId);
    }

    // --- Mapper ---
    private VehiculoDTO mapToDTO(Vehiculo vehiculo){
        return new VehiculoDTO(
            vehiculo.getId(),
            vehiculo.getPatente(),
            vehiculo.getMarca(),
            vehiculo.getModelo(),
            vehiculo.getAnio(),
            vehiculo.getKilometros(),
            vehiculo.getCliente().getDni(),
            vehiculo.getCliente().getNombre(),
            vehiculo.getCliente().getApellido(),
            vehiculo.getCliente().getId()
        );
    }
}
