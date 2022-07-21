package io.github.guilhermemelo01.msavaliadorcredito.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    @RequestMapping(method = RequestMethod.GET)
    public String status(){
        return "OK";
    }


}
