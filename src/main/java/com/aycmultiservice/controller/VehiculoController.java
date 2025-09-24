package com.aycmultiservice.controller;

import com.aycmultiservice.dto.VehiculoDTO;
import com.aycmultiservice.dto.VehiculoRequestDTO;
import com.aycmultiservice.model.Vehiculo;
import com.aycmultiservice.repository.VehiculoRepository;
import com.aycmultiservice.service.VehiculoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // Listar todos los vehículos sin importar el cliente
    @GetMapping
    public List<VehiculoDTO> getAllVehiculos(){
        return vehiculoService.getAllVehiculos();
    }

    // Crear un vehículo asociado a un cliente
    @PostMapping("/{clienteId}")
    public VehiculoDTO addVehiculo(@PathVariable Long clienteId, @RequestBody VehiculoRequestDTO vehiculoRequest){
        return vehiculoService.addVehiculoToCliente(clienteId, vehiculoRequest);
    }

    // Listar vehículos de un cliente
    @GetMapping("/cliente/{clienteId}")
    public List<VehiculoDTO> getVehiculosByCliente(@PathVariable Long clienteId) {
        return vehiculoService.getVehiculosByCliente(clienteId);
    }
}
