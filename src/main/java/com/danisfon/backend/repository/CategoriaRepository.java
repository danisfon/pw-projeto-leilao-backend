package com.danisfon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danisfon.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
