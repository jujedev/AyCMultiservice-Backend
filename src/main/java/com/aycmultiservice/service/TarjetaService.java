package com.aycmultiservice.service;

import com.aycmultiservice.dto.TarjetaDTO;
import com.aycmultiservice.dto.TarjetaRequestDTO;
import com.aycmultiservice.dto.TareaDTO;
import com.aycmultiservice.dto.TareaRequestDTO;

import java.util.List;

public interface TarjetaService {

    // Tarjetas
    List<TarjetaDTO> getAll();
    TarjetaDTO getById(Long id);
    TarjetaDTO create(TarjetaRequestDTO request);
    TarjetaDTO update(Long id, TarjetaRequestDTO request);
    void delete(Long id);

    // Tareas
    TareaDTO addTarea(Long tarjetaId, TareaRequestDTO request);
    TareaDTO updateTarea(Long tarjetaId, Long tareaId, TareaRequestDTO request);
    void deleteTarea(Long tarjetaId, Long tareaId);
}
