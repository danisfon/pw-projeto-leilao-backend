package com.danisfon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danisfon.backend.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    
}
