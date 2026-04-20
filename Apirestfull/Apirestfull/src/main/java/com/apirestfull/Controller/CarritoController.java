package com.apirestfull.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarritoController {

    @GetMapping("/carrito")
    public String mostrarCarrito(){
        // Cambiado a "Carrito" para hacer match con templates/Carrito.html
        return "Carrito";
    }
}
