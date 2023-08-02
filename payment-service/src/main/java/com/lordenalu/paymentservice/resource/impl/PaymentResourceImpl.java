package com.lordenalu.paymentservice.resource.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lordenalu.paymentservice.model.Payment;
import com.lordenalu.paymentservice.resource.PaymentResource;
import com.lordenalu.paymentservice.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/payments")
public class PaymentResourceImpl implements PaymentResource {

//	Tipo é a interface mas o Spring identifica e traz a classe de implementação anotada com @service.
	private final PaymentService service;

	@Override
	public ResponseEntity<Payment> payment(Payment payment) {
		service.sendPayment(payment);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
