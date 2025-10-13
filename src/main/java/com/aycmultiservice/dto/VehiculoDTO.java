package com.aycmultiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {
    private Long id;
    private String patente;
    private String marca;
    private String modelo;
    private int anio;
    private Long kilometros;

    private String clienteDni;
    private String clienteNombre;
    private String clienteApellido;
    private Long clienteId;
}
