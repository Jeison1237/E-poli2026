package com.apirestfull.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_items")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación muchos a uno con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relación muchos a uno con Producto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private int cantidad;

    // --- Constructores ---
    public CarritoItem() {}

    public CarritoItem(Usuario usuario, Producto producto, int cantidad) {
        this.usuario = usuario;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // --- Métodos utilitarios ---
    @Override
    public String toString() {
        return "CarritoItem{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getUsername() : "null") +
                ", producto=" + (producto != null ? producto.getNombre() : "null") +
                ", cantidad=" + cantidad +
                '}';
    }
}