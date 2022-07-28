package io.github.guilhermemelo01.mscartoes.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartao {

    private Long idCartao;
    private String cpf;
    private String enderenco;
    private BigDecimal limiteLiberado;

}
