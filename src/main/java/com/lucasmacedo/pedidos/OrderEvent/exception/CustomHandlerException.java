package com.lucasmacedo.pedidos.OrderEvent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomHandlerException {

    @ExceptionHandler(OrderException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleOrderException(OrderException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitulo(ex.getTitulo());
        errorResponse.setDetalhe(ex.getDetalhe());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
