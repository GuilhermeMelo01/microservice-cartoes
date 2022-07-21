package io.github.guilhermemelo01.msavaliadorcredito.service;

import io.github.guilhermemelo01.msavaliadorcredito.domain.model.DadosCliente;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.guilhermemelo01.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesCliente;

    public SituacaoCliente obterSituacaoCliente(String cpf){
        //obter dados dos cliente - MSCLIENTE
        //obter cartoes do cliente - MSCARTOES

        ResponseEntity<DadosCliente> dadosClienteResponse = clientesCliente.dadoCliente(cpf);
        return SituacaoCliente.builder()
                .cliente(dadosClienteResponse.getBody())
                .build();
    }
}
