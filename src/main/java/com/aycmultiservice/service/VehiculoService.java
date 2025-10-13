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

    public VehiculoDTO addVehiculoToCliente(Long clienteId, VehiculoRequestDTO vehiculoRequest){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatente(vehiculoRequest.getPatente());
        vehiculo.setMarca(vehiculoRequest.getMarca());
        vehiculo.setModelo(vehiculoRequest.getModelo());
        vehiculo.setAnio(vehiculoRequest.getAnio());
        vehiculo.setCliente(cliente);

        Vehiculo saved = vehiculoRepository.save(vehiculo);
        return mapToDTO(saved);
    }

    public List<VehiculoDTO> getVehiculosByCliente(Long clienteId){
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));
        return cliente.getVehiculos().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<VehiculoDTO> getAllVehiculos(){
        return vehiculoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

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
            vehiculo.getCliente().getDni(),
            vehiculo.getCliente().getNombre(),
            vehiculo.getCliente().getApellido()
        );
    }
}
