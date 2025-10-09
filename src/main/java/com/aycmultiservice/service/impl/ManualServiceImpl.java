package com.aycmultiservice.service.impl;

import com.aycmultiservice.dto.ManualDTO;
import com.aycmultiservice.dto.ManualRequestDTO;
import com.aycmultiservice.model.Manual;
import com.aycmultiservice.repository.ManualRepository;
import com.aycmultiservice.service.ManualService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManualServiceImpl implements ManualService {
    private final ManualRepository manualRepository;

    @Value("${manuales.upload-dir}")
    private String uploadDir;

    public ManualServiceImpl(ManualRepository manualRepository) {
        this.manualRepository = manualRepository;
    }

    @Override
    public ManualDTO subirManual(ManualRequestDTO request) throws IOException {
        MultipartFile archivo = request.getArchivo();
        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path ruta = Paths.get(uploadDir + File.separator + nombreArchivo);
        Files.copy(archivo.getInputStream(), ruta);

        Manual manual = Manual.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .archivoUrl("/uploads/manuales/" + nombreArchivo)
                .build();

        manualRepository.save(manual);
        return new ManualDTO(manual.getId(), manual.getNombre(), manual.getDescripcion(), manual.getArchivoUrl());
    }

    @Override
    public List<ManualDTO> listarManuales() {
        return manualRepository.findAll().stream()
                .map(m -> new ManualDTO(m.getId(), m.getNombre(), m.getDescripcion(), m.getArchivoUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public ManualDTO obtenerManual(Long id) {
        Manual manual = manualRepository.findById(id).orElseThrow();
        return new ManualDTO(manual.getId(), manual.getNombre(), manual.getDescripcion(), manual.getArchivoUrl());
    }

    @Override
    public void eliminarManual(Long id) {
        Manual manual = manualRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manual no encontrado"));

        System.out.println("📂 Carpeta configurada en application.yml: " + uploadDir);

        if (manual.getArchivoUrl() != null && !manual.getArchivoUrl().isBlank()) {
            String nombreArchivo = manual.getArchivoUrl().replace("/uploads/manuales/", "");
            Path rutaArchivo = Paths.get(uploadDir, nombreArchivo);
            System.out.println("📄 Intentando eliminar archivo en: " + rutaArchivo);

            try {
                boolean eliminado = Files.deleteIfExists(rutaArchivo);
                System.out.println(eliminado ? "🗑 Archivo eliminado correctamente." : "⚠️ Archivo no encontrado en disco.");
            } catch (IOException e) {
                System.err.println("❌ Error al eliminar el archivo físico: " + e.getMessage());
            }
        }

        manualRepository.delete(manual);
    }
}
