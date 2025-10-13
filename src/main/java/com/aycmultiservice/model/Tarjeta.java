package com.aycmultiservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String numeroTarjeta;     // Nro único

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
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

    // Relación con tareas (lo dejamos preparado)
    @OneToMany(mappedBy = "tarjeta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas;
}