package com.danisfon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danisfon.backend.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
