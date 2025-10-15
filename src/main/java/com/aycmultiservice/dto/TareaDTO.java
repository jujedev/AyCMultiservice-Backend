package com.aycmultiservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaDTO {
    private Long id;
    private String descripcion;
    private String mecanicoAsignado;
    private Integer horas;
    private Double costoManoObra;
    private Double costoRepuestos;
    private String estado;
    private String observaciones;
}
