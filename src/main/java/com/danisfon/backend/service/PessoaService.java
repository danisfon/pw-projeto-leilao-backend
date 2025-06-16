package com.danisfon.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.danisfon.backend.model.Pessoa;
import com.danisfon.backend.repository.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MessageSource messageSource;

    public Pessoa inserir(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa alterar(Pessoa pessoa) {
        // return pessoaRepository.save(pessoa);
        Pessoa pessoaBanco = buscarPorId(pessoa.getId());
        pessoaBanco.setNome(pessoa.getNome());
        pessoaBanco.setEmail(pessoa.getEmail());

        return pessoaRepository.save(pessoaBanco);
    }

    public void excluir(long id) {
        Pessoa pessoaBanco = buscarPorId(id);

        pessoaRepository.delete(pessoaBanco);
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("pessoa.notfound", new Object[] { id }, LocaleContextHolder.getLocale())));
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }
}
