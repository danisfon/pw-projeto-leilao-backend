package com.danisfon.backend.dto;

import lombok.Data;

@Data
public class PessoaAutenticacaoDTO {
    private String nome;
    private String email;
    private String token;
    private String tipoPerfil;
}
