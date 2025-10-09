package com.aycmultiservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String archivoUrl;
}
