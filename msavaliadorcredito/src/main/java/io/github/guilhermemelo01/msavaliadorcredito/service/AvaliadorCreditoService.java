package io.github.guilhermemelo01.msavaliadorcredito.service;

import feign.FeignException;
import io.github.guilhermemelo01.msavaliadorcredito.application.ex.DadosClientesNotFoundException;
import io.github.guilhermemelo01.msavaliadorcredito.application.ex.ErroComunicacaoMicroserviceException;
import io.github.guilhermemelo01.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.*;
import io.github.guilhermemelo01.msavaliadorcredito.infra.clients.CartoesResourceCliente;
import io.github.guilhermemelo01.msavaliadorcredito.infra.clients.ClienteResourceClient;
import io.github.guilhermemelo01.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesCliente;
    private final CartoesResourceCliente cartoesCliente;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClientesNotFoundException, ErroComunicacaoMicroserviceException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesCliente.dadoCliente(cpf);
            ResponseEntity<List<CartoesCliente>> cartoesResponse = cartoesCliente.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientesNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacaoCliente(String cpf, Long renda)
            throws DadosClientesNotFoundException, ErroComunicacaoMicroserviceException {
        try {

            ResponseEntity<DadosCliente> dadosClienteResponse = clientesCliente.dadoCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesCliente.getCartoesRendaAteh(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            var listaCartoesAprovados= cartoes.stream().map(cartao -> {

                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);


                CartaoAprovado cartaoAprovado = new CartaoAprovado();
                cartaoAprovado.setCartao(cartao.getNome());
                cartaoAprovado.setBandeira(cartao.getBandeira());
                cartaoAprovado.setLimiteAprovado(limiteAprovado);

                return cartaoAprovado;
            }).toList();

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientesNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados){
        try {
            emissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (Exception e){
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }

}
