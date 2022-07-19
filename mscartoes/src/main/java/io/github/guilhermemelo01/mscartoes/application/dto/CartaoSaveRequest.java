package io.github.guilhermemelo01.mscartoes.application.dto;

import io.github.guilhermemelo01.mscartoes.domain.BandeiraCartao;
import io.github.guilhermemelo01.mscartoes.domain.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
