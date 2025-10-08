package com.aycmultiservice.service.impl;

import com.aycmultiservice.dto.TarjetaDTO;
import com.aycmultiservice.dto.TarjetaRequestDTO;
import com.aycmultiservice.model.Cliente;
import com.aycmultiservice.model.Vehiculo;
import com.aycmultiservice.model.Tarjeta;
import com.aycmultiservice.repository.ClienteRepository;
import com.aycmultiservice.repository.VehiculoRepository;
import com.aycmultiservice.repository.TarjetaRepository;
import com.aycmultiservice.service.TarjetaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarjetaServiceImpl implements TarjetaService {
    private final TarjetaRepository tarjetaRepository;
    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;

    public TarjetaServiceImpl(TarjetaRepository tarjetaRepository,
                              ClienteRepository clienteRepository,
                              VehiculoRepository vehiculoRepository) {
        this.tarjetaRepository = tarjetaRepository;
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<TarjetaDTO> getAll() {
        return tarjetaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public TarjetaDTO getById(Long id) {
        return tarjetaRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
    }

    @Override
    public TarjetaDTO create(TarjetaRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Vehiculo vehiculo = vehiculoRepository.findById(request.getVehiculoId())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setCliente(cliente);
        tarjeta.setVehiculo(vehiculo);
        tarjeta.setFalla(request.getFalla());
        tarjeta.setObservaciones(request.getObservaciones());
        tarjeta.setFechaIngreso(request.getFechaIngreso());
        tarjeta.setFechaFinalizacion(request.getFechaFinalizacion());
        tarjeta.setPrioridad(request.getPrioridad());
        tarjeta.setTipoServicio(request.getTipoServicio());
        tarjeta.setKilometraje(request.getKilometraje());
        tarjeta.setPresupuestoEstimado(request.getPresupuestoEstimado());
        tarjeta.setMecanicoAsignado(request.getMecanicoAsignado());
        tarjeta.setEstado("Borrador");

        // Generar número de tarjeta único
        tarjeta.setNumeroTarjeta("TAR-" + System.currentTimeMillis());

        return toDTO(tarjetaRepository.save(tarjeta));
    }

    @Override
    public TarjetaDTO update(Long id, TarjetaRequestDTO request) {
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        tarjeta.setFalla(request.getFalla());
        tarjeta.setObservaciones(request.getObservaciones());
        tarjeta.setFechaIngreso(request.getFechaIngreso());
        tarjeta.setFechaFinalizacion(request.getFechaFinalizacion());
        tarjeta.setPrioridad(request.getPrioridad());
        tarjeta.setTipoServicio(request.getTipoServicio());
        tarjeta.setKilometraje(request.getKilometraje());
        tarjeta.setPresupuestoEstimado(request.getPresupuestoEstimado());
        tarjeta.setMecanicoAsignado(request.getMecanicoAsignado());

        return toDTO(tarjetaRepository.save(tarjeta));
    }

    @Override
    public void delete(Long id) {
        tarjetaRepository.deleteById(id);
    }

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
        return dto;
    }
}
