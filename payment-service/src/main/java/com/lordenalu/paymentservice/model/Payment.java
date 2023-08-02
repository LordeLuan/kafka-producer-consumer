package com.lordenalu.paymentservice.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class Payment implements Serializable {
	private static final long serialVersionUID = 4317969896862891435L;

	private Long id;
	private Long idUser;
	private Long idProduct;
	private String cardNumber;
}
