package com.lordeluan.strproducer.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import lombok.RequiredArgsConstructor;

@Log4j2
@RequiredArgsConstructor
@Service
public class StringProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;
	
//	Enviando mensagem para o kafka e logando em caso de sucesso e ou falha
	public void sendMessage(String message) {
		kafkaTemplate.send("str-topic", message).addCallback(
			success -> {
				if(success != null) {
					log.info("Send message with success {}", message);
					log.info("Partition {}, offSet {}", 
								success.getRecordMetadata().partition(),
								success.getRecordMetadata().offset());
				}
			},
			error -> log.error("Error send menssage")
		);
	}
}
