package com.aycmultiservice.service;

import com.aycmultiservice.model.Cliente;
import com.aycmultiservice.model.Vehiculo;
import com.aycmultiservice.repository.ClienteRepository;
import com.aycmultiservice.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository, ClienteRepository clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Vehiculo addVehiculoToCliente(Long clienteId, Vehiculo vehiculo){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));
        vehiculo.setCliente(cliente);
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> getVehiculosByCliente(Long clienteId){
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));
        return cliente.getVehiculos();
    }
}
