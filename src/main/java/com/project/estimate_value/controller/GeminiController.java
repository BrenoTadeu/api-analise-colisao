package com.project.estimate_value.controller;

import com.project.estimate_value.DTO.RespostaGemini;
import com.project.estimate_value.service.GeminiService;
import com.project.estimate_value.service.OrcamentoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService gemini;
    private final OrcamentoService orcamentoService;

    private static final Set<String> PECAS_VALIDAS = Set.of(
            "Capô", "Teto", "Para-lama Direito", "Para-lama Esquerdo",
            "Porta Dianteira Direita", "Porta Dianteira Esquerda", "Porta Traseira Direita",
            "Porta Traseira Esquerda", "Lateral Direita", "Lateral Esquerda",
            "Polaina Direita", "Polaina Esquerda", "Para-choque Dianteiro",
            "Para-choque Traseiro", "Tampa do Porta-malas", "Coluna A Direita",
            "Coluna A Esquerda", "Coluna B Direita", "Coluna B Esquerda",
            "Coluna C Direita", "Coluna C Esquerda"
    );

    public GeminiController(GeminiService gemini, OrcamentoService orcamentoService) {
        this.gemini = gemini;
        this.orcamentoService = orcamentoService;
    }

    @PostMapping(
            value = "/analisar",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RespostaGemini> analisarImagem(
            @RequestParam("arquivo") MultipartFile[] arquivo
    ) throws IOException {

        RespostaGemini respostaIa = gemini.analisarImage(arquivo);

        validarRespostaIA(respostaIa);

        BigDecimal valorTotal = orcamentoService.calcular(respostaIa);


        RespostaGemini resultado = new RespostaGemini(
                respostaIa.descricaoIA(),
                respostaIa.pecasDanificadas(),
                respostaIa.statusDano(),
                valorTotal
        );

        return ResponseEntity.ok(resultado);
    }

    private void validarRespostaIA(RespostaGemini respostaIa) {
        if (respostaIa.pecasDanificadas() != null) {
            for (String peca : respostaIa.pecasDanificadas()) {
                if (!PECAS_VALIDAS.contains(peca)) {
                    throw new IllegalArgumentException(
                            "Peça inválida retornada pela IA: " + peca
                    );
                }
            }
        }

        if (!Set.of("LEVE", "MODERADO", "CRITICO").contains(respostaIa.statusDano())) {
            throw new IllegalArgumentException(
                    "Gravidade inválida: " + respostaIa.statusDano()
            );
        }
    }
}