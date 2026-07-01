package com.project.estimate_value.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "analise")
@Data
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private String telefoneCliente;
    @Column(columnDefinition = "TEXT")
    private String descricaoIa;
    @Column(columnDefinition = "TEXT")
    private String pecasDanificadas;
    @Enumerated(EnumType.STRING)
    private GravidadeAnalise gravidade;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorEstimado;
    private Integer quantidadeFotos;
    @Enumerated(EnumType.STRING)
    private StatusAnalise status;
    private LocalDateTime dataCriacao;
    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }


}
