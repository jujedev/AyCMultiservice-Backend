package com.aycmultiservice.service;

import com.aycmultiservice.dto.ManualDTO;
import com.aycmultiservice.dto.ManualRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ManualService {
    ManualDTO subirManual(ManualRequestDTO manualRequest) throws IOException;
    List<ManualDTO> listarManuales();
    ManualDTO obtenerManual(Long id);
    void eliminarManual(Long id);
}
