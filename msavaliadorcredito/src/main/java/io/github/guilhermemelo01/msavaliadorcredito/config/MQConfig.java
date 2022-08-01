package io.github.guilhermemelo01.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesFila;

    //Aqui vai ser a queue onde eu vou mandar publicar minhas mensagens
    @Bean
    public Queue queueEmissaoCartoes(){
        return new Queue(emissaoCartoesFila, true);
    }
}
