package com.danisfon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danisfon.backend.model.Lance;

public interface LanceRepository extends JpaRepository<Lance, Long> {
    
}
