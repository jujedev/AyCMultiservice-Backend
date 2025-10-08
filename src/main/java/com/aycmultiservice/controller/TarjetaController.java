package com.aycmultiservice.controller;

import com.aycmultiservice.dto.TarjetaDTO;
import com.aycmultiservice.dto.TarjetaRequestDTO;
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

    @GetMapping
    public List<TarjetaDTO> getAll() {
        return tarjetaService.getAll();
    }

    @GetMapping("/{id}")
    public TarjetaDTO getById(@PathVariable Long id) {
        return tarjetaService.getById(id);
    }

    @PostMapping
    public TarjetaDTO create(@RequestBody TarjetaRequestDTO request) {
        return tarjetaService.create(request);
    }

    @PutMapping("/{id}")
    public TarjetaDTO update(@PathVariable Long id, @RequestBody TarjetaRequestDTO request) {
        return tarjetaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tarjetaService.delete(id);
    }
}