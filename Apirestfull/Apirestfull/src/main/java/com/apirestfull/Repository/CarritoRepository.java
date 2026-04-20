package com.apirestfull.Repository;

import com.apirestfull.Model.CarritoItem;
import com.apirestfull.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<CarritoItem, Long> {
    List<CarritoItem> findByUsuario(Usuario usuario);
}