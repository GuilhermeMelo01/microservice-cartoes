package io.github.guilhermemelo01.msavaliadorcredito.infra.clients;

import io.github.guilhermemelo01.msavaliadorcredito.domain.model.CartoesCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceCliente {

    @RequestMapping(params = "cpf", method = RequestMethod.GET)
    ResponseEntity<List<CartoesCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);
}
