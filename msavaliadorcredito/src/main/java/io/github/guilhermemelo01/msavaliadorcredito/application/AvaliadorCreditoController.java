package io.github.guilhermemelo01.msavaliadorcredito.application;

import io.github.guilhermemelo01.msavaliadorcredito.application.ex.DadosClientesNotFoundException;
import io.github.guilhermemelo01.msavaliadorcredito.application.ex.ErroComunicacaoMicroserviceException;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.DadosAvaliacao;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import io.github.guilhermemelo01.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.guilhermemelo01.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @RequestMapping(method = RequestMethod.GET)
    public String status() {
        return "OK";
    }

    @RequestMapping(value = "situacao-cliente", params = "cpf", method = RequestMethod.GET)
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        SituacaoCliente situacaoCliente = null;
        try {
            situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClientesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacaoCliente(
                    dados.getCpf(),
                    dados.getRenda());
           return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClientesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
