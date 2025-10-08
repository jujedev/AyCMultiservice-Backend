package com.aycmultiservice.service;

import com.aycmultiservice.dto.TareaDTO;
import com.aycmultiservice.dto.TareaRequestDTO;

import java.util.List;

public interface TareaService {
    List<TareaDTO> getAll();
    List<TareaDTO> getByTarjetaId(Long tarjetaId);
    TareaDTO create(TareaRequestDTO request);
    TareaDTO update(Long id, TareaRequestDTO request);
    void delete(Long id);
}
