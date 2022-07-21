package io.github.guilhermemelo01.msavaliadorcredito.infra.clients;

import io.github.guilhermemelo01.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @RequestMapping(params = "cpf", method = RequestMethod.GET)
    ResponseEntity<DadosCliente> dadoCliente(@RequestParam("cpf") String cpf);

}
