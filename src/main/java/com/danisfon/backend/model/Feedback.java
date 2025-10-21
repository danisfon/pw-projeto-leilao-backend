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
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "{validation.campo.notblank}")
    private int nota;
    @NotBlank(message = "{validation.data.notblank}")
    private LocalDateTime dataHora;
    @NotBlank(message = "{validation.comentario.notblank}")
    private String comentario;

    // @ManyToOne
    // @JoinColumn(name = "id_pessoa_autor")
    // private Pessoa autor;

    // @ManyToOne
    // @JoinColumn(name = "id_pessoa_destinatario")
    // private Pessoa destinatario;

}
