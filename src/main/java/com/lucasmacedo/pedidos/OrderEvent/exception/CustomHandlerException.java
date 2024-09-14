package com.lucasmacedo.pedidos.OrderEvent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class CustomHandlerException {

    @ExceptionHandler(OrderException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleOrderException(OrderException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitulo(ex.getTitulo());
        errorResponse.setDetalhe(ex.getDetalhe());
        errorResponse.setCodigo(ex.getCodigo());

        HttpStatus status = HttpStatus.valueOf(ex.getCodigo());

        return new ResponseEntity<>(errorResponse, status);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitulo("Método Não Permitido");
        errorResponse.setDetalhe("O método HTTP solicitado não é suportado pelo recurso.");
        errorResponse.setCodigo(HttpStatus.METHOD_NOT_ALLOWED.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitulo("Recurso Não Encontrado");
        errorResponse.setDetalhe("O recurso solicitado não foi encontrado. Verifique o endpoint e tente novamente.");
        errorResponse.setCodigo(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitulo("Argumento Inválido");
        errorResponse.setDetalhe(ex.getMessage());
        errorResponse.setCodigo(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
