package io.github.guilhermemelo01.mscartoes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //Mostra que esse MS é um cliente eureka
@EnableRabbit //O Boot faz a configuracao do RabbitMQ
@Slf4j
public class MscartoesApplication {

	public static void main(String[] args) {
		log.info("Iformação: {}", "teste info");
		log.error("Error: {}", "teste error");
		log.warn("Aviso: {}", "teste warn");
		SpringApplication.run(MscartoesApplication.class, args);
	}

}
