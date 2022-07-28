package io.github.guilhermemelo01.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartao {

    private Long idCartao;
    private String cpf;
    private String enderenco;
    private BigDecimal limiteLiberado;

}
