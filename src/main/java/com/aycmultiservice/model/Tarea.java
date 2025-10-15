package com.aycmultiservice.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

    private String mecanicoAsignado;

    private Integer horas;

    private Double costoManoObra;

    private Double costoRepuestos;

    private String estado;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id", nullable = false)
    @JsonBackReference
    private Tarjeta tarjeta;
}
