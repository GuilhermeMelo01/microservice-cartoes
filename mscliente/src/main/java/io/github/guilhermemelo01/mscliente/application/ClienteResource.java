package io.github.guilhermemelo01.mscliente.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClienteResource {

    @GetMapping
    public String status(){
        return "ok";
    }
}
