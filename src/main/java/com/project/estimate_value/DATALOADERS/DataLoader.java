package com.project.estimate_value.DATALOADERS;

import com.project.estimate_value.model.PrecoAnalise;
import com.project.estimate_value.repository.PrecoAnaliseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner carregarPrecosIniciais(PrecoAnaliseRepository repository) {
        return args -> {
            if (repository.count() == 0) {

                System.out.println("Inserindo valores na database");

                // CAPÔ
                repository.save(new PrecoAnalise("Capô", "LEVE", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Capô", "MODERADO", new BigDecimal("1200.00")));
                repository.save(new PrecoAnalise("Capô", "CRITICO", new BigDecimal("1500.00")));

                // PARA-LAMAS
                repository.save(new PrecoAnalise("Para-lama Esquerdo", "LEVE", new BigDecimal("700.00")));
                repository.save(new PrecoAnalise("Para-lama Esquerdo", "MODERADO", new BigDecimal("800.00")));
                repository.save(new PrecoAnalise("Para-lama Esquerdo", "CRITICO", new BigDecimal("900.00")));

                repository.save(new PrecoAnalise("Para-lama Direito", "LEVE", new BigDecimal("700.00")));
                repository.save(new PrecoAnalise("Para-lama Direito", "MODERADO", new BigDecimal("800.00")));
                repository.save(new PrecoAnalise("Para-lama Direito", "CRITICO", new BigDecimal("900.00")));

                // PORTA DIANTEIRA ESQUERDA
                repository.save(new PrecoAnalise("Porta Dianteira Esquerda", "LEVE", new BigDecimal("850.00")));
                repository.save(new PrecoAnalise("Porta Dianteira Esquerda", "MODERADO", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Porta Dianteira Esquerda", "CRITICO", new BigDecimal("1300.00")));

                // PORTA DIANTEIRA DIREITA
                repository.save(new PrecoAnalise("Porta Dianteira Direita", "LEVE", new BigDecimal("850.00")));
                repository.save(new PrecoAnalise("Porta Dianteira Direita", "MODERADO", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Porta Dianteira Direita", "CRITICO", new BigDecimal("1300.00")));

                // PORTA TRASEIRA ESQUERDA
                repository.save(new PrecoAnalise("Porta Traseira Esquerda", "LEVE", new BigDecimal("850.00")));
                repository.save(new PrecoAnalise("Porta Traseira Esquerda", "MODERADO", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Porta Traseira Esquerda", "CRITICO", new BigDecimal("1300.00")));

                // PORTA TRASEIRA DIREITA
                repository.save(new PrecoAnalise("Porta Traseira Direita", "LEVE", new BigDecimal("850.00")));
                repository.save(new PrecoAnalise("Porta Traseira Direita", "MODERADO", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Porta Traseira Direita", "CRITICO", new BigDecimal("1300.00")));

                // POLAINA DIREITA
                repository.save(new PrecoAnalise("Polaina Direita", "LEVE", new BigDecimal("850.00")));
                repository.save(new PrecoAnalise("Polaina Direita", "MODERADO", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Polaina Direita", "CRITICO", new BigDecimal("1300.00")));

                // POLAINA ESQUERDA
                repository.save(new PrecoAnalise("Polaina Esquerda", "LEVE", new BigDecimal("850.00")));
                repository.save(new PrecoAnalise("Polaina Esquerda", "MODERADO", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Polaina Esquerda", "CRITICO", new BigDecimal("1300.00")));

                // TETO
                repository.save(new PrecoAnalise("Teto", "LEVE", new BigDecimal("1200.00")));
                repository.save(new PrecoAnalise("Teto", "MODERADO", new BigDecimal("1500.00")));
                repository.save(new PrecoAnalise("Teto", "CRITICO", new BigDecimal("1800.00")));

                // TAMPA
                repository.save(new PrecoAnalise("Tampa", "LEVE", new BigDecimal("800.00")));
                repository.save(new PrecoAnalise("Tampa", "MODERADO", new BigDecimal("950.00")));
                repository.save(new PrecoAnalise("Tampa", "CRITICO", new BigDecimal("1200.00")));

                // LATERAL
                repository.save(new PrecoAnalise("Lateral Direita", "LEVE", new BigDecimal("800.00")));
                repository.save(new PrecoAnalise("Lateral Direita", "MODERADO", new BigDecimal("900.00")));
                repository.save(new PrecoAnalise("Lateral Direita", "CRITICO", new BigDecimal("1000.00")));

                repository.save(new PrecoAnalise("Lateral Esquerda", "LEVE", new BigDecimal("800.00")));
                repository.save(new PrecoAnalise("Lateral Esquerda", "MODERADO", new BigDecimal("900.00")));
                repository.save(new PrecoAnalise("Lateral Esquerda", "CRITICO", new BigDecimal("1000.00")));

                // PARA-CHOQUE
                repository.save(new PrecoAnalise("Para-choque Dianteiro", "LEVE", new BigDecimal("750.00")));
                repository.save(new PrecoAnalise("Para-choque Dianteiro", "MODERADO", new BigDecimal("800.00")));
                repository.save(new PrecoAnalise("Para-choque Dianteiro", "CRITICO", new BigDecimal("900.00")));

                repository.save(new PrecoAnalise("Para-choque Traseiro", "LEVE", new BigDecimal("750.00")));
                repository.save(new PrecoAnalise("Para-choque Traseiro", "MODERADO", new BigDecimal("800.00")));
                repository.save(new PrecoAnalise("Para-choque Traseiro", "CRITICO", new BigDecimal("900.00")));

                // COLUNA A ESQUERDA
                repository.save(new PrecoAnalise("Coluna A Esquerda", "LEVE", new BigDecimal("900.00")));
                repository.save(new PrecoAnalise("Coluna A Esquerda", "MODERADO", new BigDecimal("1200.00")));
                repository.save(new PrecoAnalise("Coluna A Esquerda", "CRITICO", new BigDecimal("1800.00")));

                // COLUNA A DIREITA
                repository.save(new PrecoAnalise("Coluna A Direita", "LEVE", new BigDecimal("900.00")));
                repository.save(new PrecoAnalise("Coluna A Direita", "MODERADO", new BigDecimal("1200.00")));
                repository.save(new PrecoAnalise("Coluna A Direita", "CRITICO", new BigDecimal("1800.00")));

                // COLUNA B ESQUERDA
                repository.save(new PrecoAnalise("Coluna B Esquerda", "LEVE", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Coluna B Esquerda", "MODERADO", new BigDecimal("1400.00")));
                repository.save(new PrecoAnalise("Coluna B Esquerda", "CRITICO", new BigDecimal("2200.00")));

                // COLUNA B DIREITA
                repository.save(new PrecoAnalise("Coluna B Direita", "LEVE", new BigDecimal("1000.00")));
                repository.save(new PrecoAnalise("Coluna B Direita", "MODERADO", new BigDecimal("1400.00")));
                repository.save(new PrecoAnalise("Coluna B Direita", "CRITICO", new BigDecimal("2200.00")));

                // COLUNA C ESQUERDA
                repository.save(new PrecoAnalise("Coluna C Esquerda", "LEVE", new BigDecimal("950.00")));
                repository.save(new PrecoAnalise("Coluna C Esquerda", "MODERADO", new BigDecimal("1300.00")));
                repository.save(new PrecoAnalise("Coluna C Esquerda", "CRITICO", new BigDecimal("2000.00")));

                // COLUNA C DIREITA
                repository.save(new PrecoAnalise("Coluna C Direita", "LEVE", new BigDecimal("950.00")));
                repository.save(new PrecoAnalise("Coluna C Direita", "MODERADO", new BigDecimal("1300.00")));
                repository.save(new PrecoAnalise("Coluna C Direita", "CRITICO", new BigDecimal("2000.00")));

                System.out.println(">> Preços carregados com sucesso!");
            }
        };
    }
}