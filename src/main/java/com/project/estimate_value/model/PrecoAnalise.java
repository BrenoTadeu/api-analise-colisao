package com.project.estimate_value.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "precoAnalise")
@Data
public class PrecoAnalise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String componente;

    @Column(nullable = false)
    private String tipoDano;

    @Column(name="valor_target", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTarget;

    public PrecoAnalise() {
    }

    public PrecoAnalise(String componente, String tipoDano, BigDecimal valorTarget) {
        this.componente = componente;
        this.tipoDano = tipoDano;
        this.valorTarget = valorTarget;
    }
}
