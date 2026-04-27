package com.apirestfull.Controller;

import com.apirestfull.client.ProductClient;
import com.apirestfull.client.OrderClient;
import com.apirestfull.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private ProductClient productClient;
    
    @Autowired
    private OrderClient orderClient;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String dashboard(Model model) {
        try {
            // Get basic statistics for dashboard
            long totalProductos = productClient.listarProductos().size();
            long totalOrdenes = orderClient.getAllOrders().size();
            long totalUsuarios = usuarioRepository.count();
            
            model.addAttribute("totalProductos", totalProductos);
            model.addAttribute("totalOrdenes", totalOrdenes);
            model.addAttribute("totalUsuarios", totalUsuarios);
            
            return "admin-dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar estadísticas: " + e.getMessage());
            return "admin-dashboard";
        }
    }
    
    @GetMapping("/productos")
    public String productos(Model model) {
        try {
            model.addAttribute("productos", productClient.listarProductos());
            return "admin-productos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar productos: " + e.getMessage());
            return "admin-productos";
        }
    }
    
    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        try {
            model.addAttribute("ordenes", orderClient.getAllOrders());
            return "admin-ordenes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar órdenes: " + e.getMessage());
            return "admin-ordenes";
        }
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        try {
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "admin-usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar usuarios: " + e.getMessage());
            return "admin-usuarios";
        }
    }
    
    @GetMapping("/reportes")
    public String reportes(Model model) {
        return "admin-reportes";
    }
}