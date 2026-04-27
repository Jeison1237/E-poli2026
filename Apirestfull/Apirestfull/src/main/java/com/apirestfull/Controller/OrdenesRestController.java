package com.apirestfull.Controller;

import com.apirestfull.Model.Order;
import com.apirestfull.client.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenesRestController {
    
    private final OrderClient orderClient;
    
    @GetMapping
    public List<Order> getAllOrders() {
        return orderClient.getAllOrders();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderClient.getOrderById(id);
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public List<Order> getOrdersByUser(@PathVariable Long usuarioId) {
        return orderClient.getOrdersByUser(usuarioId);
    }
    
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderClient.createOrder(order);
    }
    
    @PutMapping("/{id}/estado")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody Order order) {
        return orderClient.updateOrderStatus(id, order);
    }
}