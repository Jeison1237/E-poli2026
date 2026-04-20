package com.apirestfull.client;

import com.apirestfull.Controller.ProductoController;
import com.apirestfull.Model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<Producto> listarProductos();

    @GetMapping("/products/{id}")
    ResponseEntity<Producto> getProductoById(@PathVariable("id") Long id);

    @PostMapping("/products")
    Producto createProducto(@RequestBody Producto producto);

    @PutMapping("/products/{id}")
    ResponseEntity<Producto> updateProducto(@PathVariable("id") Long id, @RequestBody Producto productoDetails);

    @DeleteMapping("/products/{id}")
    ResponseEntity<Void> deleteProducto(@PathVariable("id") Long id);

    @PostMapping("/products/reduce-stock")
    ResponseEntity<?> reduceStock(@RequestBody List<ProductoController.CompraItem> items);
}
