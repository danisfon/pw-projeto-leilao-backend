package com.danisfon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danisfon.backend.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
