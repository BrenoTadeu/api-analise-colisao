package com.project.estimate_value.service;

import com.project.estimate_value.DTO.RespostaGemini;
import com.project.estimate_value.model.PrecoAnalise;
import com.project.estimate_value.repository.PrecoAnaliseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrcamentoService {

    private final PrecoAnaliseRepository precoRepository;

    public OrcamentoService(PrecoAnaliseRepository precoRepository) {
        this.precoRepository = precoRepository;
    }

    private String corrigirPeca(String peca) {
        return switch (peca.toLowerCase()) {
            case "paralama direito" -> "Para-lama Direito";
            case "paralama esquerdo" -> "Para-lama Esquerdo";
            case "parachoque dianteiro" -> "Para-choque Dianteiro";
            case "parachoque traseiro" -> "Para-choque Traseiro";
            case "capo" -> "Capô";
            default -> peca;
        };
    }

    public BigDecimal calcular(RespostaGemini respostaIa) {
        List<String> pecasCorrigidas = respostaIa.pecasDanificadas()
                .stream()
                .map(String::trim)
                .map(this::corrigirPeca)
                .toList();

        String gravidade = respostaIa.statusDano().trim();

        System.out.println("Peças Recebidas e Corrigidas: " + pecasCorrigidas);
        System.out.println("Gravidade geral: " + gravidade);

        BigDecimal totalComponentes = BigDecimal.ZERO;

        for (String peca : pecasCorrigidas) {
            PrecoAnalise precoBase = precoRepository
                    .findByComponenteAndTipoDano(peca, gravidade)
                    .orElseThrow(() -> new RuntimeException(
                            "Não encontramos preço cadastrado para: " + peca + " com dano " + gravidade
                    ));

            totalComponentes = totalComponentes.add(precoBase.getValorTarget());

            System.out.println(totalComponentes);
        }

        BigDecimal taxaMaoDeObra = new BigDecimal("100.00");

        return totalComponentes.add(taxaMaoDeObra);
    }
}