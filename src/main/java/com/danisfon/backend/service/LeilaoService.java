package com.danisfon.backend.service;

import com.danisfon.backend.model.Leilao;
import com.danisfon.backend.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class LeilaoService {

    @Autowired
    private LeilaoRepository leilaoRepository;

    public Leilao criar(Leilao leilao) {
        return leilaoRepository.save(leilao);
    }

    public Leilao buscarPorId(Long id) {
        return leilaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leilao não encontrado"));
    }

    public List<Leilao> listarTodos() {
        return leilaoRepository.findAll();
    }

    public Leilao atualizar(Long id, Leilao leilao) {
        Leilao existente = buscarPorId(id);
        existente.setTitulo(leilao.getTitulo());
        existente.setDescricao(leilao.getDescricao());
        existente.setDescricaoDetalhada(leilao.getDescricaoDetalhada());
        existente.setDataHoraInicio(leilao.getDataHoraInicio());
        existente.setDataHoraFim(leilao.getDataHoraFim());
        existente.setStatus(leilao.getStatus());
        existente.setObservacao(leilao.getObservacao());
        existente.setValorIncremento(leilao.getValorIncremento());
        existente.setLanceMinimo(leilao.getLanceMinimo());
        existente.setCategoria(leilao.getCategoria());
        existente.setPessoa(leilao.getPessoa());
        return leilaoRepository.save(existente);
    }

    public void deletar(Long id) {
        log.info("Deletando leilao com ID: {}", id);
        log.warn("Atenção: Ao excluir um leilao, você não poderá acessá-lo novamente.");
        Leilao leilao = buscarPorId(id);
        leilaoRepository.delete(leilao);
    }
}