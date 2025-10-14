package com.aycmultiservice.dto;

import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarjetaDTO {
    private Long id;
    private String numeroTarjeta;
    private String clienteNombre;
    private String vehiculoPatente;
    private String falla;
    private String observaciones;
    private LocalDate fechaIngreso;
    private LocalDate fechaFinalizacion;
    private String estado;
    private String prioridad;
    private String tipoServicio;
    private Integer kilometraje;
    private Double presupuestoEstimado;
    private Double montoFinal;
    private String mecanicoAsignado;
}
