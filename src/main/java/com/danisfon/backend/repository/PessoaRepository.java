package com.danisfon.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// import org.hibernate.query.Page;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.danisfon.backend.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    // @Query("from Pessoa where email=:email")
    // public Page<Pessoa> buscarEmail(@Param("email"))

    public Optional<Pessoa> findByEmail(String email);
}
