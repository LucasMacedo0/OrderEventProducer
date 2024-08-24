package com.lucasmacedo.pedidos.OrderEvent.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderException extends RuntimeException{

    private String detalhe;
    private String titulo;
}
