package com.aycmultiservice.service.impl;

import com.aycmultiservice.dto.*;
import com.aycmultiservice.exception.ResourceNotFoundException;
import com.aycmultiservice.model.*;
import com.aycmultiservice.repository.*;
import com.aycmultiservice.service.TarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TarjetaServiceImpl implements TarjetaService {
    private final TarjetaRepository tarjetaRepository;
    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;
    private final TareaRepository tareaRepository;

    // Tarjetas
    @Override
    public List<TarjetaDTO> getAll() {
        return tarjetaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TarjetaDTO getById(Long id) {
        return tarjetaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No existe tarjeta con ID " + id));
    }

    @Override
    public TarjetaDTO create(TarjetaRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe cliente con ID " + request.getClienteId()));

        Vehiculo vehiculo = vehiculoRepository.findById(request.getVehiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe vehiculo con ID " + request.getClienteId()));

        Tarjeta tarjeta = new Tarjeta();
        // Generar número de tarjeta único
        tarjeta.setNumeroTarjeta("TAR-" + System.currentTimeMillis());
        tarjeta.setCliente(cliente);
        tarjeta.setVehiculo(vehiculo);
        tarjeta.setFalla(request.getFalla());
        tarjeta.setObservaciones(request.getObservaciones());
        tarjeta.setFechaIngreso(request.getFechaIngreso());
        tarjeta.setFechaFinalizacion(request.getFechaFinalizacion());
        tarjeta.setEstado("Borrador");
        tarjeta.setPrioridad(request.getPrioridad());
        tarjeta.setTipoServicio(request.getTipoServicio());
        tarjeta.setKilometraje(request.getKilometraje());
        tarjeta.setPresupuestoEstimado(request.getPresupuestoEstimado());
        tarjeta.setMecanicoAsignado(request.getMecanicoAsignado());
        tarjeta.setMontoFinal(0.0);

        return toDTO(tarjetaRepository.save(tarjeta));
    }

    @Override
    public TarjetaDTO update(Long id, TarjetaRequestDTO request) {
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe tarjeta con ID " + id));

        tarjeta.setFalla(request.getFalla());
        tarjeta.setObservaciones(request.getObservaciones());
        tarjeta.setFechaIngreso(request.getFechaIngreso());
        tarjeta.setFechaFinalizacion(request.getFechaFinalizacion());
        tarjeta.setPrioridad(request.getPrioridad());
        tarjeta.setTipoServicio(request.getTipoServicio());
        tarjeta.setKilometraje(request.getKilometraje());
        tarjeta.setPresupuestoEstimado(request.getPresupuestoEstimado());
        tarjeta.setMecanicoAsignado(request.getMecanicoAsignado());

        actualizarEstadoTarjeta(tarjeta);
        recalcularMontoFinal(tarjeta);

        return toDTO(tarjetaRepository.save(tarjeta));
    }

    @Override
    public void delete(Long id) {
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("No existe tarjeta con ID " + id));
        tarjetaRepository.deleteById(id);
    }

    // Tareas
    @Override
    public TareaDTO addTarea(Long tarjetaId, TareaRequestDTO req) {
        Tarjeta tarjeta = findTarjetaOrThrow(tarjetaId);

        Tarea tarea = Tarea.builder()
                .descripcion(req.getDescripcion())
                .mecanicoAsignado(req.getMecanicoAsignado())
                .horas(req.getHoras())
                .costoManoObra(req.getCostoManoObra())
                .costoRepuestos(req.getCostoRepuestos())
                .estado(req.getEstado())
                .observaciones(req.getObservaciones())
                .tarjeta(tarjeta)
                .build();

        tareaRepository.save(tarea);

        actualizarEstadoTarjeta(tarjeta);
        recalcularMontoFinal(tarjeta);
        tarjetaRepository.save(tarjeta);

        return toDTO(tarea);
    }

    @Override
    public TareaDTO updateTarea(Long tarjetaId, Long tareaId, TareaRequestDTO req) {
        Tarjeta tarjeta = findTarjetaOrThrow(tarjetaId);
        Tarea tarea = findTareaOrThrow(tareaId, tarjetaId);

        tarea.setDescripcion(req.getDescripcion());
        tarea.setMecanicoAsignado(req.getMecanicoAsignado());
        tarea.setHoras(req.getHoras());
        tarea.setCostoManoObra(req.getCostoManoObra());
        tarea.setCostoRepuestos(req.getCostoRepuestos());
        tarea.setEstado(req.getEstado());
        tarea.setObservaciones(req.getObservaciones());

        tareaRepository.save(tarea);

        actualizarEstadoTarjeta(tarjeta);
        recalcularMontoFinal(tarjeta);
        tarjetaRepository.save(tarjeta);

        return toDTO(tarea);
    }

    @Override
    public void deleteTarea(Long tarjetaId, Long tareaId) {
        Tarjeta tarjeta = findTarjetaOrThrow(tarjetaId);
        Tarea tarea = findTareaOrThrow(tareaId, tarjetaId);

        tareaRepository.delete(tarea);

        actualizarEstadoTarjeta(tarjeta);
        recalcularMontoFinal(tarjeta);
        tarjetaRepository.save(tarjeta);
    }

    // Utils
    private Tarjeta findTarjetaOrThrow(Long id) {
        return tarjetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe tarjeta con ID " + id));
    }

    private Tarea findTareaOrThrow(Long tareaId, Long tarjetaId) {
        return tareaRepository.findById(tareaId)
                .filter(t -> t.getTarjeta().getId().equals(tarjetaId))
                .orElseThrow(() -> new ResourceNotFoundException("No existe tarea con ID " + tareaId + " en la tarjeta " + tarjetaId));
    }

    private void recalcularMontoFinal(Tarjeta tarjeta) {
        double total = tarjeta.getTareas().stream()
                .mapToDouble(t -> safe(t.getCostoManoObra()) + safe(t.getCostoRepuestos()))
                .sum();
        tarjeta.setMontoFinal(total);
    }

    private void actualizarEstadoTarjeta(Tarjeta tarjeta) {
        if (tarjeta.getTareas().isEmpty()) {
            tarjeta.setEstado("Pendiente");
            return;
        }

        boolean algunaEnProceso = tarjeta.getTareas().stream().anyMatch(t -> "En proceso".equals(t.getEstado()));
        boolean algunaEnDiagnostico = tarjeta.getTareas().stream().anyMatch(t -> "En diagnóstico".equals(t.getEstado()));
        boolean algunaEnEspera = tarjeta.getTareas().stream().anyMatch(t -> "En espera de repuestos".equals(t.getEstado()));
        boolean algunaNoReparable = tarjeta.getTareas().stream().anyMatch(t -> "No se puede reparar".equals(t.getEstado()));
        boolean todasCanceladas = tarjeta.getTareas().stream().allMatch(t -> "Cancelada".equals(t.getEstado()));
        boolean todasFinalizadas = tarjeta.getTareas().stream().allMatch(t -> "Finalizada".equals(t.getEstado()));

        if (algunaNoReparable) tarjeta.setEstado("No se puede reparar");
        else if (algunaEnDiagnostico) tarjeta.setEstado("En diagnóstico");
        else if (algunaEnEspera) tarjeta.setEstado("En espera de repuestos");
        else if (algunaEnProceso) tarjeta.setEstado("En proceso");
        else if (todasFinalizadas) tarjeta.setEstado("Finalizada");
        else if (todasCanceladas) tarjeta.setEstado("Cancelada");
        else tarjeta.setEstado("En proceso");
    }

    private double safe(Double n) {
        return n == null ? 0.0 : n;
    }
    // Mappers
    private TarjetaDTO toDTO(Tarjeta tarjeta) {
        TarjetaDTO dto = new TarjetaDTO();
        dto.setId(tarjeta.getId());
        dto.setNumeroTarjeta(tarjeta.getNumeroTarjeta());
        dto.setClienteNombre(tarjeta.getCliente().getNombre());
        dto.setVehiculoPatente(tarjeta.getVehiculo().getPatente());
        dto.setFalla(tarjeta.getFalla());
        dto.setObservaciones(tarjeta.getObservaciones());
        dto.setFechaIngreso(tarjeta.getFechaIngreso());
        dto.setFechaFinalizacion(tarjeta.getFechaFinalizacion());
        dto.setEstado(tarjeta.getEstado());
        dto.setPrioridad(tarjeta.getPrioridad());
        dto.setTipoServicio(tarjeta.getTipoServicio());
        dto.setKilometraje(tarjeta.getKilometraje());
        dto.setPresupuestoEstimado(tarjeta.getPresupuestoEstimado());
        dto.setMontoFinal(tarjeta.getMontoFinal());
        dto.setMecanicoAsignado(tarjeta.getMecanicoAsignado());
        dto.setTareas(tarjeta.getTareas().stream().map(this::toDTO).collect(Collectors.toList()));
        return dto;
    }

    private TareaDTO toDTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setMecanicoAsignado(tarea.getMecanicoAsignado());
        dto.setHoras(tarea.getHoras());
        dto.setCostoManoObra(tarea.getCostoManoObra());
        dto.setCostoRepuestos(tarea.getCostoRepuestos());
        dto.setEstado(tarea.getEstado());
        dto.setObservaciones(tarea.getObservaciones());
        return dto;
    }
}
