package com.aycmultiservice.controller;

import com.aycmultiservice.dto.ManualDTO;
import com.aycmultiservice.dto.ManualRequestDTO;
import com.aycmultiservice.service.ManualService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/manuales")
//@CrossOrigin(origins = "*")
public class ManualController {
    private final ManualService manualService;

    public ManualController(ManualService manualService) {
        this.manualService = manualService;
    }

    @PostMapping
    public ManualDTO subirManual(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("archivo") MultipartFile archivo
    ) throws IOException {
        ManualRequestDTO request = new ManualRequestDTO(nombre, descripcion, archivo);
        return manualService.subirManual(request);
    }

    @GetMapping
    public List<ManualDTO> listarManuales() {
        return manualService.listarManuales();
    }

    @GetMapping("/{id}")
    public ManualDTO obtenerManual(@PathVariable Long id) {
        return manualService.obtenerManual(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarManual(@PathVariable Long id) {
        manualService.eliminarManual(id);
    }
}
