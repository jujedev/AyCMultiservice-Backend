package com.aycmultiservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "manuales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String archivoUrl;
}
