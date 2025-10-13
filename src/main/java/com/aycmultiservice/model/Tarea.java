package com.aycmultiservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tareas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Double costo;
    private Integer horas;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;
}
