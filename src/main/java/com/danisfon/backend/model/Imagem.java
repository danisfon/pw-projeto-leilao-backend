package com.danisfon.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "imagem")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "{validation.data.notblank}")
    private LocalDateTime dataHoraCadastro;
    @NotBlank(message = "{validation.campo.notblank}")
    private String nomeImagem;

    @ManyToOne
    @JoinColumn(name = "id_leilao")
    private Leilao leilao;

}
