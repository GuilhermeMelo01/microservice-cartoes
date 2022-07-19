package io.github.guilhermemelo01.mscartoes.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartoes")
public class CartoesResource {

    @RequestMapping(method = RequestMethod.GET)
    public String status(){
        return "OK";
    }

}
