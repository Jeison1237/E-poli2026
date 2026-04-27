package com.UserService.controller;

import com.UserService.model.Usuario;
import com.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping("/registro")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioRegistrado = userService.register(usuario);
            // Don't return password in response
            usuarioRegistrado.setPassword(null);
            return ResponseEntity.ok(usuarioRegistrado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            
            if (usuario != null) {
                // Don't return password in response
                usuario.setPassword(null);
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.badRequest().body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en el login");
        }
    }
    
    @GetMapping
    public List<Usuario> getAllUsers() {
        List<Usuario> usuarios = userService.findAll();
        // Remove passwords from response
        usuarios.forEach(u -> u.setPassword(null));
        return usuarios;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(usuario -> {
                    usuario.setPassword(null); // Don't return password
                    return ResponseEntity.ok(usuario);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<Usuario> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(usuario -> {
                    usuario.setPassword(null); // Don't return password
                    return ResponseEntity.ok(usuario);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(usuario -> {
                    usuario.setPassword(null); // Don't return password
                    return ResponseEntity.ok(usuario);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        try {
            Usuario usuarioActualizado = userService.update(id, usuarioDetails);
            usuarioActualizado.setPassword(null); // Don't return password
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // DTO for login request
    public static class LoginRequest {
        private String username;
        private String password;
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}