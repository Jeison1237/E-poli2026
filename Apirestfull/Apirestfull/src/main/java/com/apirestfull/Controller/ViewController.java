package com.apirestfull.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @GetMapping("/")
    public String landing() {
        return "index"; // Landing page pública
    }

    @GetMapping("/productos")
    public String productos() {
        return "productos"; // Página CRUD de productos
    }
    
    @GetMapping("/carrito")
    public String carrito() {
        return "Carrito"; // Página del carrito
    }
    
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
    
    @GetMapping("/mis-ordenes")
    public String misOrdenes() {
        return "mis-ordenes";
    }
    
    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }
    
    @GetMapping("/detalle-producto/{id}")
    public String detalleProducto(@PathVariable Long id) {
        return "detalle-producto";
    }
}