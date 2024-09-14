package com.lucasmacedo.pedidos.OrderEvent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lucasmacedo.pedidos.OrderEvent")
public class OrderEventProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderEventProducerApplication.class, args);
	}

}
