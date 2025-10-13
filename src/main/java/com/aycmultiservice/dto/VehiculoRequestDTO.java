package com.aycmultiservice.dto;

public class VehiculoRequestDTO {
    private String patente;
    private String marca;
    private String modelo;
    private int anio;
    private Long kilometros;
    private Long clienteId;

    // Getters y setters
    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getKilometros() { return kilometros; }
    public void setKilometros(Long kilometros) { this.kilometros = kilometros; }
}
