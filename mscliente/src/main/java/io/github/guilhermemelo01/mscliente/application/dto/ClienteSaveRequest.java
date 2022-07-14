package io.github.guilhermemelo01.mscliente.application.dto;

import io.github.guilhermemelo01.mscliente.domain.Cliente;
import lombok.Data;

@Data
public class ClienteSaveRequest {

    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
