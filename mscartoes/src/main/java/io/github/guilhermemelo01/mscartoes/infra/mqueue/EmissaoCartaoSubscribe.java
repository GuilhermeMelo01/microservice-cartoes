package io.github.guilhermemelo01.mscartoes.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscribe {

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")//Define qual fila esse metodo vai ficar escutando
    public void receberSolicitacaoEmissao(@Payload String payload){
        System.out.println(payload);
    }

}
