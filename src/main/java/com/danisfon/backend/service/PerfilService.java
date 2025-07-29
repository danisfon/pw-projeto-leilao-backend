package com.danisfon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.danisfon.backend.exception.NaoEncontradoExcecao;

import com.danisfon.backend.model.Perfil;
import com.danisfon.backend.repository.PerfilRepository;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private MessageSource messageSource;

    public Perfil inserir(Perfil perfil) {
        Perfil perfilCadastrado = perfilRepository.save(perfil);
        return perfilCadastrado;
    }

    public Perfil alterar(Perfil perfil) {
        Perfil perfilBanco = buscarPorId(perfil.getId());
        perfilBanco.setNome(perfil.getNome());

        return perfilRepository.save(perfilBanco);
    }

    public void excluir(long id) {
        Perfil perfilBanco = buscarPorId(id);
        perfilRepository.delete(perfilBanco);
    }

    public Perfil buscarPorId(Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("perfil.notfound",
                        new Object[] { id }, LocaleContextHolder.getLocale())));
    }

    public Page<Perfil> buscarTodos(Pageable pageable) {
        return perfilRepository.findAll(pageable);
    }

}
