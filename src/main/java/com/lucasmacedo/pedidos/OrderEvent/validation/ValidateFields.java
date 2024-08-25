package com.lucasmacedo.pedidos.OrderEvent.validation;

import com.lucasmacedo.pedidos.OrderEvent.exception.OrderException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class ValidateFields {

    private final Validator validator;

    public ValidateFields(Validator validator) {
        this.validator = validator;
    }

    public void validate(Object object) throws OrderException {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Object> violation : violations) {
                String field = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                sb.append(String.format("Campo '%s': %s; ",  field, message));
            }
            throw new OrderException(sb.toString(), "Erros de validação", HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
    }
}
