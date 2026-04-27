package com.apirestfull.client;

import com.apirestfull.Model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {

    @PostMapping("/orders")
    Order createOrder(@RequestBody Order order);
    
    @GetMapping("/orders")
    List<Order> getAllOrders();
    
    @GetMapping("/orders/{id}")
    ResponseEntity<Order> getOrderById(@PathVariable("id") Long id);
    
    @GetMapping("/orders/usuario/{usuarioId}")
    List<Order> getOrdersByUser(@PathVariable("usuarioId") Long usuarioId);
    
    @PutMapping("/orders/{id}/estado")
    ResponseEntity<Order> updateOrderStatus(@PathVariable("id") Long id, @RequestBody Order order);
}
