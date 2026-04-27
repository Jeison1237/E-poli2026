package com.OrderService.repository;

import com.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsuarioIdOrderByFechaDesc(Long usuarioId);
    List<Order> findByEstado(String estado);
}

