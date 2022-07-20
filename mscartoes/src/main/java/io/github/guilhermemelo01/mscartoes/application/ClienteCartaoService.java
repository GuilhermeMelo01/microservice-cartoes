package io.github.guilhermemelo01.mscartoes.application;

import io.github.guilhermemelo01.mscartoes.domain.ClienteCartao;
import io.github.guilhermemelo01.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartaoByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
