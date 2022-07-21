package io.github.guilhermemelo01.msavaliadorcredito.application;

import io.github.guilhermemelo01.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.guilhermemelo01.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @RequestMapping(method = RequestMethod.GET)
    public String status(){
        return "OK";
    }

    @RequestMapping(value = "situacao-cliente", params = "cpf", method = RequestMethod.GET)
    public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
        return ResponseEntity.ok(situacaoCliente);
    }


}
