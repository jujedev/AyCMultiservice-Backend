package com.aycmultiservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tarjetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroTarjeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    private String falla;
    private String observaciones;

    private LocalDate fechaIngreso;
    private LocalDate fechaFinalizacion;

    private String estado;            // Borrador, En proceso, etc.
    private String prioridad;         // Baja, Media, Alta, Urgente
    private String tipoServicio;      // Mecánica, electricidad, etc.

    private Integer kilometraje;
    private Double presupuestoEstimado;
    private Double montoFinal;

    private String mecanicoAsignado;

    @OneToMany(
            mappedBy = "tarjeta",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Tarea> tareas = new ArrayList<>();
}