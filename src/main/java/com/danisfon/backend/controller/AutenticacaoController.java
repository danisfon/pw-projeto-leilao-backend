package com.danisfon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danisfon.backend.dto.PessoaAutenticacaoDTO;
import com.danisfon.backend.dto.PessoaRequisicaoDTO;
import com.danisfon.backend.service.AutenticacaoService;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<PessoaAutenticacaoDTO> login(@RequestBody PessoaRequisicaoDTO pessoa) {
        return ResponseEntity.ok(autenticacaoService.autenticar(pessoa));
    }

}
