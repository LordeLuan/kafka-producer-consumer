package com.lordenalu.paymentservice.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KafkaAdminConfig {

//	Pega as informações do Kafka no arquivo properties
	public final KafkaProperties properties;

//	Faz conexão com o kafka cluster
	@Bean
	public KafkaAdmin kafkaAdmin() {
		var configs = new HashMap<String, Object>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
		return new KafkaAdmin(configs);
	}

//	Cria um novo topico para envio de mensagens com 2 partições e 1 replica
	@Bean
	public KafkaAdmin.NewTopics newTopics() {
		return new KafkaAdmin.NewTopics(
				TopicBuilder.name("payment-topic").partitions(1).replicas(1).build()
				);
	}
}