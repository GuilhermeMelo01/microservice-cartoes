package io.github.guilhermemelo01.msavaliadorcredito.service;

import feign.FeignException;
import io.github.guilhermemelo01.msavaliadorcredito.application.ex.DadosClientesNotFoundException;
import io.github.guilhermemelo01.msavaliadorcredito.application.ex.ErroComunicacaoMicroserviceException;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.CartoesCliente;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.DadosCliente;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.guilhermemelo01.msavaliadorcredito.infra.clients.CartoesResourceCliente;
import io.github.guilhermemelo01.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesCliente;
    private final CartoesResourceCliente cartoesCliente;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClientesNotFoundException, ErroComunicacaoMicroserviceException {
        try{
        ResponseEntity<DadosCliente> dadosClienteResponse = clientesCliente.dadoCliente(cpf);
        ResponseEntity<List<CartoesCliente>> cartoesResponse = cartoesCliente.getCartoesByCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .cartoes(cartoesResponse.getBody())
                .build();
    }catch (FeignException.FeignClientException e){
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClientesNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }
}
