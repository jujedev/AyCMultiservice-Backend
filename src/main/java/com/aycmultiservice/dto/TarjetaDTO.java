package com.aycmultiservice.dto;

import java.time.LocalDate;

public class TarjetaDTO {
    private Long id;
    private String numeroTarjeta;
    private String clienteNombre;
    private String vehiculoPatente;
    private String falla;
    private String observaciones;
    private LocalDate fechaIngreso;
    private LocalDate fechaFinalizacion;
    private String estado;
    private String prioridad;
    private String tipoServicio;
    private Integer kilometraje;
    private Double presupuestoEstimado;
    private Double montoFinal;
    private String mecanicoAsignado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getVehiculoPatente() {
        return vehiculoPatente;
    }

    public void setVehiculoPatente(String vehiculoPatente) {
        this.vehiculoPatente = vehiculoPatente;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Double getPresupuestoEstimado() {
        return presupuestoEstimado;
    }

    public void setPresupuestoEstimado(Double presupuestoEstimado) {
        this.presupuestoEstimado = presupuestoEstimado;
    }

    public Double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(Double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public String getMecanicoAsignado() {
        return mecanicoAsignado;
    }

    public void setMecanicoAsignado(String mecanicoAsignado) {
        this.mecanicoAsignado = mecanicoAsignado;
    }
}
