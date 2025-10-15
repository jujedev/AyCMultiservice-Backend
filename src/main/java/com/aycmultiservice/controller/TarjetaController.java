package com.aycmultiservice.controller;

import com.aycmultiservice.dto.TarjetaDTO;
import com.aycmultiservice.dto.TarjetaRequestDTO;
import com.aycmultiservice.dto.TareaDTO;
import com.aycmultiservice.dto.TareaRequestDTO;
import com.aycmultiservice.service.TarjetaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    private final TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }


    // # CRUD TARJETAS

    // Create Tarjeta
    @PostMapping
    public TarjetaDTO create(@RequestBody TarjetaRequestDTO request) {
        return tarjetaService.create(request);
    }

    // Read Tarjetas
    @GetMapping
    public List<TarjetaDTO> getAll() {
        return tarjetaService.getAll();
    }

    // Read Tarjeta by Id
    @GetMapping("/{id}")
    public TarjetaDTO getById(@PathVariable Long id) {
        return tarjetaService.getById(id);
    }

    // Update Tarjeta
    @PutMapping("/{id}")
    public TarjetaDTO update(@PathVariable Long id, @RequestBody TarjetaRequestDTO request) {
        return tarjetaService.update(id, request);
    }

    // Delete Tarjeta
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tarjetaService.delete(id);
    }

    // # TAREAS DENTRO DE TARJETA

    // Crear tarea
    @PostMapping("/{tarjetaId}/tareas")
    public TareaDTO addTarea(@PathVariable Long tarjetaId, @RequestBody TareaRequestDTO request) {
        return tarjetaService.addTarea(tarjetaId, request);
    }

    // Editar tarea
    @PutMapping("/{tarjetaId}/tareas/{tareaId}")
    public TareaDTO updateTarea(
            @PathVariable Long tarjetaId,
            @PathVariable Long tareaId,
            @RequestBody TareaRequestDTO request) {
        return tarjetaService.updateTarea(tarjetaId, tareaId, request);
    }

    // Eliminar tarea
    @DeleteMapping("/{tarjetaId}/tareas/{tareaId}")
    public void deleteTarea(@PathVariable Long tarjetaId, @PathVariable Long tareaId) {
        tarjetaService.deleteTarea(tarjetaId, tareaId);
    }
}