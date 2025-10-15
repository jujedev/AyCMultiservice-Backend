package com.aycmultiservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaRequestDTO {
    private String descripcion;
    private String mecanicoAsignado;
    private Integer horas;
    private Double costoManoObra;
    private Double costoRepuestos;
    private String estado;
    private String observaciones;
}
