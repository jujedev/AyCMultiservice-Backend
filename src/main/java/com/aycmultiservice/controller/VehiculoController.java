package com.aycmultiservice.controller;

import com.aycmultiservice.model.Vehiculo;
import com.aycmultiservice.repository.VehiculoRepository;
import com.aycmultiservice.service.VehiculoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;
    private final VehiculoRepository vehiculoRepository;

    public VehiculoController(VehiculoService vehiculoService,  VehiculoRepository vehiculoRepository) {
        this.vehiculoService = vehiculoService;
        this.vehiculoRepository = vehiculoRepository;
    }

    // Listar todos los vehículos sin importar el cliente
    @GetMapping
    public List<Vehiculo> getAllVehiculos(){
        return vehiculoRepository.findAll();
    }

    // Crear un vehículo asociado a un cliente
    @PostMapping("/{clienteId}")
    public Vehiculo addVehiculo(@PathVariable Long clienteId, @RequestBody Vehiculo vehiculo){
        return vehiculoService.addVehiculoToCliente(clienteId, vehiculo);
    }

    // Listar vehículos de un cliente
    @GetMapping("/cliente/{clienteId}")
    public List<Vehiculo> getVehiculosByCliente(@PathVariable Long clienteId) {
        return vehiculoService.getVehiculosByCliente(clienteId);
    }
}
