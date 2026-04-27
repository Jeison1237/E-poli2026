package com.apirestfull.Controller;

import com.apirestfull.Model.Usuario;
import com.apirestfull.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {
    
    private final UserClient userClient;
    
    @GetMapping
    public List<Usuario> getAllUsers() {
        return userClient.getAllUsers();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return userClient.getUserById(id);
    }
    
    @PostMapping("/registro")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        return userClient.register(usuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        return userClient.updateUser(id, usuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userClient.deleteUser(id);
    }
}