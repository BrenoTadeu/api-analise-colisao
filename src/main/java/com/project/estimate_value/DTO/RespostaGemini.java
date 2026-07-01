package com.project.estimate_value.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.math.BigDecimal;
import java.util.List;

public record RespostaGemini(
        @JsonProperty("descricaoIa")
        String descricaoIA ,
        @JsonProperty("pecasDanificadas")
        List<String> pecasDanificadas,
        @JsonProperty("gravidade")
        String statusDano,
        @JsonProperty("valor_target")
        BigDecimal valorTotal
) {
}
