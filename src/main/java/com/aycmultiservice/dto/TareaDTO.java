package com.aycmultiservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaDTO {
    private Long id;
    private String descripcion;
    private Double costo;
    private Integer horas;
    private String estado;
    private String numeroTarjeta;
}
