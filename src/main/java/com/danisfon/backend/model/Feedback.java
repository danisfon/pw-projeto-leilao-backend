package com.danisfon.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
