package com.aycmultiservice.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculoRequestDTO {
    private String patente;
    private String marca;
    private String modelo;
    private int anio;
    private Long kilometros;
    private Long clienteId;
}
