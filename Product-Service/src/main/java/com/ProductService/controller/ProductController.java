package com.ProductService.controller;

import com.ProductService.model.Producto;
import com.ProductService.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> listProducts() {
        return productoRepository.findAll();
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoDetails.getNombre());
                    producto.setPrecio(productoDetails.getPrecio());
                    producto.setCantidad(productoDetails.getCantidad());
                    return ResponseEntity.ok(productoRepository.save(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public static class StockReductionRequest {
        private Long id; // Antes productoId
        private int cantidad;

        // Getters y Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }

    @PostMapping("/reduce-stock")
    public ResponseEntity<?> reduceStock(@RequestBody List<StockReductionRequest> items) {
        for (StockReductionRequest item : items) {
            // Ahora usamos item.getId()
            Producto producto = productoRepository.findById(item.getId()).orElse(null);
            if (producto == null) {
                return ResponseEntity.badRequest().body("Producto no encontrado: id=" + item.getId());
            }
            if (producto.getCantidad() < item.getCantidad()) {
                return ResponseEntity.badRequest().body("Stock insuficiente para " + producto.getNombre());
            }
            producto.setCantidad(producto.getCantidad() - item.getCantidad());
            productoRepository.save(producto);
        }
        return ResponseEntity.ok().build();
    }
}
