package com.apirestfull.Service;

import com.apirestfull.Model.Usuario;
import com.apirestfull.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        
        // Create authorities based on user role
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String rol = usuario.getRol();
        if (rol != null) {
            // Add ROLE_ prefix for Spring Security
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol));
        } else {
            // Default role if none specified
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        
        return new org.springframework.security.core.userdetails.User(
            usuario.getUsername(), 
            usuario.getPassword(), 
            authorities
        );
    }
}

