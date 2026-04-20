package com.apirestfull.Controller;

import com.apirestfull.Model.Producto;
import com.apirestfull.client.OrderClient;
import com.apirestfull.client.ProductClient;
import com.apirestfull.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderClient orderClient;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productClient.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productClient.getProductoById(id);
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productClient.createProducto(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        return productClient.updateProducto(id, productoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        return productClient.deleteProducto(id);
    }

    // DTO para recibir los items de la compra
    public static class CompraItem {
        private Long id;
        private int cantidad;
        private Long usuarioId; // Asumiendo que el ID de usuario viene en la petición

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
        public Long getUsuarioId() { return usuarioId; }
        public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    }

    @PostMapping("/compra")
    public ResponseEntity<?> procesarCompra(@RequestBody List<CompraItem> items) {
        // 1. Llamar a product-service para reducir el stock.
        // Este endpoint ya contiene la lógica de validación.
        ResponseEntity<?> stockResponse = productClient.reduceStock(items);

        // Si la reducción de stock falla, devolver el error.
        if (!stockResponse.getStatusCode().is2xxSuccessful()) {
            return stockResponse;
        }

        // 2. Si el stock se redujo correctamente, registrar las órdenes.
        for (CompraItem item : items) {
            Order newOrder = new Order();
            newOrder.setProductoId(item.getId());
            newOrder.setCantidad(item.getCantidad());
            newOrder.setUsuarioId(item.getUsuarioId()); // Asignar el ID de usuario
            orderClient.createOrder(newOrder);
        }

        return ResponseEntity.ok("Compra procesada exitosamente");
    }
}
