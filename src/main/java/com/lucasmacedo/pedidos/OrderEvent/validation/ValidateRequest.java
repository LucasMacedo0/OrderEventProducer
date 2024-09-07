package com.lucasmacedo.pedidos.OrderEvent.validation;

import com.lucasmacedo.pedidos.OrderEvent.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateRequest {

    public void validaRequest(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new OrderException("Falha na Requisição", "ID não pode ser nulo ou vazio", HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
    }
}