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

    // Obtener vehiculo por Id
    @GetMapping("/{vehiculoId}")
    public VehiculoDTO getVehiculoById(@PathVariable Long vehiculoId){
        return vehiculoService.getVehiculoById(vehiculoId);
    }

    // Listar vehículos de un cliente
    @GetMapping("/cliente/{clienteId}")
    public List<VehiculoDTO> getVehiculosByCliente(@PathVariable Long clienteId) {
        return vehiculoService.getVehiculosByCliente(clienteId);
    }

    // Crear un vehículo asociado a un cliente
    @PostMapping("/{clienteId}")
    public VehiculoDTO addVehiculo(@PathVariable Long clienteId, @RequestBody VehiculoRequestDTO vehiculoRequest){
        return vehiculoService.addVehiculoToCliente(clienteId, vehiculoRequest);
    }

    // Actualizar vehículo (también sirve para reasignar cliente)
    @PutMapping("/{vehiculoId}")
    public VehiculoDTO updateVehiculo(@PathVariable Long vehiculoId, @RequestBody VehiculoRequestDTO vehiculoRequest){
        return vehiculoService.updateVehiculo(vehiculoId, vehiculoRequest);
    }

    // Eliminar un vehículo por ID
    @DeleteMapping("/{vehiculoId}")
    public void deleteVehiculo(@PathVariable Long vehiculoId) {
        vehiculoService.deleteVehiculo(vehiculoId);
    }
}
