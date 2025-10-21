package com.danisfon.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danisfon.backend.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    Optional<Perfil> findByNome(String nome);
}
