package com.aycmultiservice.service.impl;

import com.aycmultiservice.dto.TareaDTO;
import com.aycmultiservice.dto.TareaRequestDTO;
import com.aycmultiservice.model.Tarjeta;
import com.aycmultiservice.model.Tarea;
import com.aycmultiservice.repository.TarjetaRepository;
import com.aycmultiservice.repository.TareaRepository;
import com.aycmultiservice.service.TareaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;
    private final TarjetaRepository tarjetaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository, TarjetaRepository tarjetaRepository) {
        this.tareaRepository = tareaRepository;
        this.tarjetaRepository = tarjetaRepository;
    }

    @Override
    public List<TareaDTO> getAll() {
        return tareaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TareaDTO> getByTarjetaId(Long tarjetaId) {
        return tareaRepository.findByTarjetaId(tarjetaId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public TareaDTO create(TareaRequestDTO request) {
        Tarjeta tarjeta = tarjetaRepository.findById(request.getTarjetaId())
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        Tarea tarea = new Tarea();
        tarea.setDescripcion(request.getDescripcion());
        tarea.setCosto(request.getCosto());
        tarea.setHoras(request.getHoras());
        tarea.setEstado(request.getEstado() != null ? request.getEstado() : "Pendiente");
        tarea.setTarjeta(tarjeta);

        return toDTO(tareaRepository.save(tarea));
    }

    @Override
    public TareaDTO update(Long id, TareaRequestDTO request) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        tarea.setDescripcion(request.getDescripcion());
        tarea.setCosto(request.getCosto());
        tarea.setHoras(request.getHoras());
        tarea.setEstado(request.getEstado());

        return toDTO(tareaRepository.save(tarea));
    }

    @Override
    public void delete(Long id) {
        tareaRepository.deleteById(id);
    }

    private TareaDTO toDTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setCosto(tarea.getCosto());
        dto.setHoras(tarea.getHoras());
        dto.setEstado(tarea.getEstado());
        dto.setNumeroTarjeta(tarea.getTarjeta().getNumeroTarjeta());
        return dto;
    }
}
