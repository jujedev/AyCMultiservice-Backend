package com.aycmultiservice.dto;

import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarjetaRequestDTO {
    private Long clienteId;
    private Long vehiculoId;
    private String falla;
    private String observaciones;
    private LocalDate fechaIngreso;
    private LocalDate fechaFinalizacion;
    private String prioridad;
    private String tipoServicio;
    private Integer kilometraje;
    private Double presupuestoEstimado;
    private String mecanicoAsignado;
    private String estado;
}
