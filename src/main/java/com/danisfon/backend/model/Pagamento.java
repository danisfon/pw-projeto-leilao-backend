package com.danisfon.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "pagamento")
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "{validation.valor.notblank}")
    private float valor;
    @NotBlank(message = "{validation.data.notblank}")
    private LocalDateTime dataHora;
    @NotBlank(message = "{validation.campo.notblank}")
    private String status;

    // @OneToOne
    // @JoinColumn(name = "id_leilao")
    // private Leilao leilao;

}
