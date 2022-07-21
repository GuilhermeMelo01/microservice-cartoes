package io.github.guilhermemelo01.msavaliadorcredito.domain.model;


import lombok.Data;

import java.util.List;

@Data
public class SituacaoCliente {

    private DadosCliente cliente;
    private List<CartoesCliente> cartoes;

}
