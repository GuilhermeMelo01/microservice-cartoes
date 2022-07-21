package io.github.guilhermemelo01.msavaliadorcredito.service;

import io.github.guilhermemelo01.msavaliadorcredito.domain.model.CartoesCliente;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.DadosCliente;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.guilhermemelo01.msavaliadorcredito.infra.clients.CartoesResourceCliente;
import io.github.guilhermemelo01.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesCliente;
    private final CartoesResourceCliente cartoesCliente;

    public SituacaoCliente obterSituacaoCliente(String cpf){
        ResponseEntity<DadosCliente> dadosClienteResponse = clientesCliente.dadoCliente(cpf);
        ResponseEntity<List<CartoesCliente>> cartoesResponse = cartoesCliente.getCartoesByCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .cartoes(cartoesResponse.getBody())
                .build();
    }
}
