package com.aycmultiservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaRequestDTO {
    private String descripcion;
    private Double costo;
    private Integer horas;
    private String estado;
    private Long tarjetaId;
}
