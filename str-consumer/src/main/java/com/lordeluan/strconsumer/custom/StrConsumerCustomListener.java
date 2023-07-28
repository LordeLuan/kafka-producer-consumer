package com.lordeluan.strconsumer.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

//	Informando a JVM para que esta interface esteja disponivell em tempo de execução
// 	Criando uma metaAnotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@KafkaListener
public @interface StrConsumerCustomListener {

//	Definindo um valor padrão para os atributos da annotation 
	
	@AliasFor(annotation = KafkaListener.class, attribute = "topics")
	String[] topics() default "str-topic";

	@AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
	String containerFactory() default "strContainerFactory";

//	Vazio para ser definido quando for utilizado
	@AliasFor(annotation = KafkaListener.class, attribute = "groupId")
	String groupId() default "";

}
