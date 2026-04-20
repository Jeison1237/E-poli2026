package com.apirestfull.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}