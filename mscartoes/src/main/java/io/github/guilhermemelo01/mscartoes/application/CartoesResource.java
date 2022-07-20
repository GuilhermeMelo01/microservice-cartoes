package io.github.guilhermemelo01.mscartoes.application;

import io.github.guilhermemelo01.mscartoes.application.dto.CartaoSaveRequest;
import io.github.guilhermemelo01.mscartoes.domain.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService service;

    @RequestMapping(method = RequestMethod.GET)
    public String status(){
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity cadastro(@RequestBody CartaoSaveRequest request){
        Cartao cartao = request.toModel();
        service.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(params = "renda", method = RequestMethod.GET)
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda){
        List<Cartao> list = service.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

}
