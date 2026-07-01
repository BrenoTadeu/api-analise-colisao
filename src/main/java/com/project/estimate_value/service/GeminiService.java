package com.project.estimate_value.service;

import com.project.estimate_value.DTO.RespostaGemini;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.Base64;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public String converterImage(MultipartFile arquivo) throws IOException {
        byte[] bytes = arquivo.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public RespostaGemini analisarImage(MultipartFile[] arquivos) throws IOException {
        System.out.println("Quantidade de arquivos recebidos: " + arquivos.length);

        for (MultipartFile arquivo : arquivos) {
            System.out.println("Nome: " + arquivo.getOriginalFilename());
            System.out.println("Tamanho: " + arquivo.getSize());
            System.out.println("Tipo: " + arquivo.getContentType());
        }

        if (arquivos == null || arquivos.length == 0) {
            throw new IllegalArgumentException("Nenhuma imagem foi enviada");
        }
        if (arquivos.length > 5) {
            throw new IllegalArgumentException("O máximo de imagens permitidas são 5");
        }

        String prompt = """
                OBJETIVO:
                
                               Identificar TODAS as peças danificadas visíveis nas imagens.
                
                               As imagens representam o mesmo veículo e devem ser analisadas em conjunto.
                
                               INSTRUÇÕES:
                
                               Identifique todas as peças visivelmente danificadas.
                               Considere riscos, amassados, deformações, trincas e danos de pintura.
                               Uma peça deve ser incluída apenas se houver evidência visual do dano.
                               Não invente peças.
                               Não utilize nomes diferentes dos permitidos.
                               Não repita a mesma peça mais de uma vez.
                               Se houver danos em múltiplas peças, liste todas.
                
                               LISTA FECHADA DE PEÇAS PERMITIDAS:
                
                               Capô
                               Teto
                               Para-lama Direito
                               Para-lama Esquerdo
                               Porta Dianteira Direita
                               Porta Dianteira Esquerda
                               Porta Traseira Direita
                               Porta Traseira Esquerda
                               Lateral Direita
                               Lateral Esquerda
                               Polaina Direita
                               Polaina Esquerda
                               Para-choque Dianteiro
                               Para-choque Traseiro
                               Tampa do Porta-malas
                               Coluna A Direita
                               Coluna A Esquerda
                               Coluna B Direita
                               Coluna B Esquerda
                               Coluna C Direita
                               Coluna C Esquerda
                
                               REGRAS ABSOLUTAS:
                
                               Toda peça deve existir exatamente na lista.
                               Não criar nomes novos.
                               Não alterar grafias.
                               Não utilizar abreviações.
                               Não combinar nome da peça com gravidade.
                               Não adicionar explicações dentro do campo de peças.
                
                               ANÁLISE DE MÚLTIPLAS IMAGENS:
                
                               Existem múltiplas imagens do mesmo veículo.
                
                               Analise TODAS AS IMAGENS antes de responder.
                
                               Combine as informações de todas as imagens em uma única resposta.
                
                               É proibido responder utilizando apenas uma imagem.
                
                               Se uma peça aparecer danificada em qualquer imagem, ela deve ser incluída.
                
                               Não ignore danos visíveis encontrados em outras imagens.
                
                               REFERÊNCIA DOS LADOS DO VEÍCULO (REGRA DE PRIORIDADE MÁXIMA):
                
                               Considere sempre os lados do veículo do ponto de vista do motorista sentado dentro do veículo.
                
                               NUNCA utilize a perspectiva da câmera ou de quem observa a fotografia.
                
                               Esta regra tem prioridade máxima sobre todas as outras.
                
                               Exemplos:
                
                               Se o dano estiver do lado do motorista, utilize ESQUERDO.
                
                               Se o dano estiver do lado do passageiro, utilize DIREITO.
                
                               Se houver qualquer dúvida, considere a posição do motorista como referência.
                
                               Nunca determine direita ou esquerda pela posição da peça na imagem.
                
                               GRAVIDADE:
                
                               Escolha exatamente um valor:
                
                               LEVE
                               MODERADO
                               CRITICO
                
                               A gravidade deve representar o dano geral do veículo considerando todas as peças identificadas.
                
                               RETORNE APENAS O JSON:
                
                               {
                               "descricaoIa": "Descrição dos danos encontrados",
                               "pecasDanificadas": [
                               "Para-choque Dianteiro",
                               "Para-lama Dianteiro Esquerdo"
                               ],
                               "gravidade": "MODERADO"
                               }
                
                               REGRAS DE SAÍDA:
                
                               Retorne somente JSON válido.
                
                               Não utilize markdown.
                
                               Não escreva explicações.
                
                               Não escreva comentários.
                
                               Não escreva texto antes ou depois do JSON.
                
                               Não omita peças visivelmente danificadas.
                
                               Analise todas as imagens antes de gerar a resposta.
                
                               Não responda até ter identificado todas as peças danificadas visíveis.
                               
                               REGRAS CRUTIAIS DE ORIENTAÇÃO ESPACIAL:
                
                               1. A definição de 'Esquerdo' e 'Direito' deve SEMPRE seguir a perspectiva do motorista sentado dentro do veículo, olhando para a frente (Padrão da Indústria Automotiva).
                
                               2. Se o carro estiver de frente para a foto: o farol que está do lado direito da imagem pertence ao lado ESQUERDO do carro.
                
                               3. Se o carro estiver de costas para a foto: o lado esquerdo da imagem corresponde ao lado ESQUERDO do carro.
                """;

        ObjectNode rootPayload = objectMapper.createObjectNode();
        ArrayNode contentsArray = rootPayload.putArray("contents");
        ObjectNode contentObject = contentsArray.addObject();
        ArrayNode partsArray = contentObject.putArray("parts");

        ObjectNode textNode = objectMapper.createObjectNode();
        textNode.put("text", prompt);
        partsArray.add(textNode);

        for (MultipartFile arquivo : arquivos) {
            String imagemBase64 = converterImage(arquivo);

            ObjectNode imagePart = objectMapper.createObjectNode();
            ObjectNode inlineData = imagePart.putObject("inlineData");
            inlineData.put("mimeType", arquivo.getContentType());
            inlineData.put("data", imagemBase64);

            partsArray.add(imagePart);
        }

        ObjectNode generationConfig = rootPayload.putObject("generationConfig");
        generationConfig.put("responseMimeType", "application/json");

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        String respostaGoogleJson = restClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(rootPayload))
                .retrieve()
                .body(String.class);

        JsonNode rootNode = objectMapper.readTree(respostaGoogleJson);
        String jsonTextoPuroDaIa = rootNode
                .path("candidates").get(0)
                .path("content")
                .path("parts").get(0)
                .path("text").asText();

        if (jsonTextoPuroDaIa.contains("```")) {
            jsonTextoPuroDaIa = jsonTextoPuroDaIa.replaceAll("```json", "").replaceAll("```", "").trim();
        }

        return objectMapper.readValue(jsonTextoPuroDaIa, RespostaGemini.class);
    }
}