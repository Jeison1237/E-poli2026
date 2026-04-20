package com.apirestfull.model;

// Esta clase es un DTO para transferir datos de órdenes.
// No es una entidad JPA en este microservicio.
public class Order {
    private Long id;
    private Long productoId;
    private Long usuarioId;
    private int cantidad;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}

