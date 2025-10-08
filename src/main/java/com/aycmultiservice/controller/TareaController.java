package com.aycmultiservice.controller;

import com.aycmultiservice.dto.TareaDTO;
import com.aycmultiservice.dto.TareaRequestDTO;
import com.aycmultiservice.service.TareaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<TareaDTO> getAll() {
        return tareaService.getAll();
    }

    @GetMapping("/tarjeta/{tarjetaId}")
    public List<TareaDTO> getByTarjeta(@PathVariable Long tarjetaId) {
        return tareaService.getByTarjetaId(tarjetaId);
    }

    @PostMapping
    public TareaDTO create(@RequestBody TareaRequestDTO request) {
        return tareaService.create(request);
    }

    @PutMapping("/{id}")
    public TareaDTO update(@PathVariable Long id, @RequestBody TareaRequestDTO request) {
        return tareaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tareaService.delete(id);
    }
}
