package com.aycmultiservice.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualRequestDTO {
    private String nombre;
    private String descripcion;
    private MultipartFile archivo;
}
