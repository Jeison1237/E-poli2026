package com.apirestfull.client;

import com.apirestfull.Model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {
    
    @PostMapping("/api/usuarios/registro")
    ResponseEntity<?> register(@RequestBody Usuario usuario);
    
    @PostMapping("/api/usuarios/login")
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest);
    
    @GetMapping("/api/usuarios")
    List<Usuario> getAllUsers();
    
    @GetMapping("/api/usuarios/{id}")
    ResponseEntity<Usuario> getUserById(@PathVariable("id") Long id);
    
    @GetMapping("/api/usuarios/username/{username}")
    ResponseEntity<Usuario> getUserByUsername(@PathVariable("username") String username);
    
    @GetMapping("/api/usuarios/email/{email}")
    ResponseEntity<Usuario> getUserByEmail(@PathVariable("email") String email);
    
    @PutMapping("/api/usuarios/{id}")
    ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody Usuario usuario);
    
    @DeleteMapping("/api/usuarios/{id}")
    ResponseEntity<?> deleteUser(@PathVariable("id") Long id);
    
    // DTO for login request
    class LoginRequest {
        private String username;
        private String password;
        
        public LoginRequest() {}
        
        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}