package com.lordenalu.paymentservice.config;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class JsonProducerConfig {

//	Pega as informações do Kafka no arquivo properties
	private final KafkaProperties properties;

//	Cria conexão com o kafka cluster e seta as classes que irão fazer a descerialização das mensagens enviadas
	@Bean
	public ProducerFactory jsonProducerFactory() {
		var configs = new HashMap<String, Object>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new JsonSerializer<>());
	}

//	O Spring fica responsável por chamar o metodo passado como parametro
	@Bean
	public KafkaTemplate<String, Serializable> kafkaTemplate(ProducerFactory jsonProducerFactory) {
		return new KafkaTemplate<>(jsonProducerFactory);
	}

}
