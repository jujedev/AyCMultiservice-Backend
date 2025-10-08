package com.aycmultiservice.service;

import com.aycmultiservice.dto.TarjetaDTO;
import com.aycmultiservice.dto.TarjetaRequestDTO;

import java.util.List;

public interface TarjetaService {
    List<TarjetaDTO> getAll();
    TarjetaDTO getById(Long id);
    TarjetaDTO create(TarjetaRequestDTO request);
    TarjetaDTO update(Long id, TarjetaRequestDTO request);
    void delete(Long id);
}
