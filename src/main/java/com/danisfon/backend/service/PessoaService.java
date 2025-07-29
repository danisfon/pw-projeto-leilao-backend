package com.danisfon.backend.service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.danisfon.backend.exception.NaoEncontradoExcecao;
import com.danisfon.backend.model.Pessoa;
import com.danisfon.backend.repository.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailService emailService;

    public Pessoa inserir(Pessoa pessoa) {
        Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
        // emailService.enviarEmailSimples(pessoaCadastrada.getEmail(),
        // "Cadastrado com Sucesso", "Cadastro no Sistema de Leilão XXX foi feito com
        // sucesso!");
        enviarEmailSucesso(pessoaCadastrada);
        return pessoaCadastrada;
    }

    private void enviarEmailSucesso(Pessoa pessoa) {
        Context context = new Context();
        context.setVariable("nome", pessoa.getNome());
        emailService.emailTemplate(pessoa.getEmail(), "Cadastro Sucesso", context, "cadastroSucesso");

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
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("pessoa.notfound",
                        new Object[] { id }, LocaleContextHolder.getLocale())));
    }

    public Page<Pessoa> buscarTodos(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }
}
