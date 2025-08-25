package com.danisfon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danisfon.backend.exception.NaoEncontradoExcecao;
import com.danisfon.backend.model.Imagem;
import com.danisfon.backend.repository.ImagemRepository;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private MessageSource messageSource;

    public Imagem inserir(Imagem imagem) {
        Imagem imagemCadastrado = imagemRepository.save(imagem);
        return imagemCadastrado;
    }

    public Imagem alterar(Imagem imagem) {
        Imagem imagemBanco = buscarPorId(imagem.getId());
        imagemBanco.setNomeImagem(imagem.getNomeImagem());

        return imagemRepository.save(imagemBanco);
    }

    public void excluir(long id) {
        Imagem imagemBanco = buscarPorId(id);
        imagemRepository.delete(imagemBanco);
    }

    public Imagem buscarPorId(Long id) {
        return imagemRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("imagem.notfound",
                new Object[]{id}, LocaleContextHolder.getLocale())));
    }

    public Page<Imagem> buscarTodos(Pageable pageable) {
        return imagemRepository.findAll(pageable);
    }

}
