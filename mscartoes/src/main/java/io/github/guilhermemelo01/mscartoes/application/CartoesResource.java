package io.github.guilhermemelo01.mscartoes.application;

import io.github.guilhermemelo01.mscartoes.application.dto.CartaoSaveRequest;
import io.github.guilhermemelo01.mscartoes.application.dto.CartoesPorClienteResponse;
import io.github.guilhermemelo01.mscartoes.domain.Cartao;
import io.github.guilhermemelo01.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @RequestMapping(method = RequestMethod.GET)
    public String status() {
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity cadastro(@RequestBody CartaoSaveRequest request) {
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(params = "renda", method = RequestMethod.GET)
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(params = "cpf", method = RequestMethod.GET)
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<ClienteCartao> clienteCartoes = clienteCartaoService.listCartaoByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = clienteCartoes.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .toList();
        return ResponseEntity.ok(resultList);
    }

}
