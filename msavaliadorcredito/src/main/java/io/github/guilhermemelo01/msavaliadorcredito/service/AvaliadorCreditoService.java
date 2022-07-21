package io.github.guilhermemelo01.msavaliadorcredito.service;

import io.github.guilhermemelo01.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    public SituacaoCliente obterSituacaoCliente(String cpf){
        //obter dados dos cliente - MSCLIENTE
        //obter cartoes do cliente - MSCARTOES

      return new SituacaoCliente();  //*
    }
}
