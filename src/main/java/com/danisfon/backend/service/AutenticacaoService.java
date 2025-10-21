package com.danisfon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.danisfon.backend.dto.PessoaAutenticacaoDTO;
import com.danisfon.backend.dto.PessoaRequisicaoDTO;
import com.danisfon.backend.model.Pessoa;
import com.danisfon.backend.repository.PessoaRepository;
import com.danisfon.backend.security.JwtService;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaAutenticacaoDTO autenticar(PessoaRequisicaoDTO pessoa) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha())
        );
        
        Pessoa pessoaBanco = pessoaRepository.findByEmail(pessoa.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Pega o nome do primeiro perfil vinculado à pessoa (ex: ADMIN)
        String tipoPerfil = pessoaBanco.getPessoaPerfil()
            .stream()
            .findFirst()
            .map(pp -> pp.getPerfil().getNome())
            .orElse("USER");

        PessoaAutenticacaoDTO autenticacaoDTO = new PessoaAutenticacaoDTO();
        autenticacaoDTO.setEmail(pessoaBanco.getEmail());
        autenticacaoDTO.setNome(pessoaBanco.getNome());
        autenticacaoDTO.setToken(jwtService.generateToken(authentication.getName()));
        autenticacaoDTO.setTipoPerfil(tipoPerfil); // 👈 adicionando aqui

        return autenticacaoDTO;
    }
}

