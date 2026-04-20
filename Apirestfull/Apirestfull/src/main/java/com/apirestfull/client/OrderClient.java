package com.apirestfull.client;

import com.apirestfull.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service")
public interface OrderClient {

    @PostMapping("/orders")
    void createOrder(@RequestBody Order order);
}
