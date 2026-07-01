package com.project.estimate_value.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Carro")
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String marcaCarro;
    @Column(nullable = false)
    private String modeloCarro;
    @Column(nullable = false)
    private LocalDate anoCarro;


}
