package com.danisfon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danisfon.backend.exception.NaoEncontradoExcecao;
import com.danisfon.backend.model.Lance;
import com.danisfon.backend.repository.LanceRepository;

@Service
public class LanceService {
    @Autowired
    private LanceRepository lanceRepository;

    @Autowired
    private MessageSource messageSource;

    public Lance inserir(Lance lance) {
        Lance lanceCadastrado = lanceRepository.save(lance);
        return lanceCadastrado;
    }

    public Lance alterar(Lance lance) {
        Lance lanceBanco = buscarPorId(lance.getId());
        lanceBanco.setValorLance(lance.getValorLance());

        return lanceRepository.save(lanceBanco);
    }

    public void excluir(long id) {
        Lance lanceBanco = buscarPorId(id);
        lanceRepository.delete(lanceBanco);
    }

    public Lance buscarPorId(Long id) {
        return lanceRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("lance.notfound",
                        new Object[] { id }, LocaleContextHolder.getLocale())));
    }

    public Page<Lance> buscarTodos(Pageable pageable) {
        return lanceRepository.findAll(pageable);
    }

}
