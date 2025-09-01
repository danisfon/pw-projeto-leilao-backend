package com.danisfon.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

import com.danisfon.backend.enums.StatusLeilao;

@Entity
@Table(name = "leilao")
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{validation.titulo.notblank}")
    private String titulo;

    @NotBlank(message = "{validation.descricao.notblank}")
    private String descricao;

    @Column(name = "descricao_detalhada")
    private String descricaoDetalhada;

    @NotNull(message = "{validation.dataHoraInicio.notnull}")
    @Future(message = "{validation.dataHoraInicio.future}")
    @Column(name = "data_hora_inicio")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "{validation.dataHoraFim.notnull}")
    @Future(message = "{validation.dataHoraFim.future}")
    @Column(name = "data_hora_fim")
    private LocalDateTime dataHoraFim;

    @NotNull(message = "{validation.status.notnull}")
    private StatusLeilao status;

    private String observacao;

    @NotNull(message = "{validation.valorIncremento.notnull}")
    @Positive(message = "{validation.valorIncremento.positive}")
    @Column(name = "valor_incremento")
    private Float valorIncremento;

    @NotNull(message = "{validation.lanceMinimo.notnull}")
    @Positive(message = "{validation.lanceMinimo.positive}")
    @Column(name = "lance_minimo")
    private Float lanceMinimo;
}
