package com.lucasmacedo.pedidos.OrderEvent.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String titulo;
    private String detalhe;
    private Integer codigo;

}
