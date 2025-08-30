package com.danisfon.backend.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.danisfon.backend.exception.NaoEncontradoExcecao;
import com.danisfon.backend.model.Pessoa;
import com.danisfon.backend.repository.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService implements UserDetailsService{
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public Pessoa inserir(Pessoa pessoa) {
        Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
        enviarEmailSucesso(pessoaCadastrada);
        return pessoaCadastrada;
    }

    private void enviarEmailSucesso(Pessoa pessoa) {
        Context context = new Context();
        context.setVariable("nome", pessoa.getNome());
        emailService.emailTemplate(pessoa.getEmail(), "Cadastro Sucesso", context, "cadastroSucesso");
    }

    public Pessoa alterar(Pessoa pessoa) {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pessoaRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Pessao não encontrada"));
    }

    @Transactional
    public void solicitarRecuperacaoSenha(String email) {
        Pessoa pessoa = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String codigo = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        pessoa.setCodigoValidacao(codigo);
        pessoa.setValidadeCodigoValidacao(LocalDateTime.now().plusMinutes(30));
        pessoaRepository.save(pessoa);

        Context context = new Context();
        context.setVariable("nome", pessoa.getNome());
        context.setVariable("codigo", codigo);

        emailService.emailTemplate(
                pessoa.getEmail(),
                "Recuperação de Senha",
                context,
                "recuperarSenha"
        );
    }

    @Transactional
    public void alterarSenhaComCodigo(String email, String codigo, String novaSenha) {
        Pessoa pessoa = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (pessoa.getCodigoValidacao() == null ||
            !pessoa.getCodigoValidacao().equals(codigo) ||
            pessoa.getValidadeCodigoValidacao().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Código inválido ou expirado");
        }

        pessoa.setSenha(encoder.encode(novaSenha));

        pessoa.setCodigoValidacao(null);
        pessoa.setValidadeCodigoValidacao(null);
        pessoaRepository.save(pessoa);
    }



}
