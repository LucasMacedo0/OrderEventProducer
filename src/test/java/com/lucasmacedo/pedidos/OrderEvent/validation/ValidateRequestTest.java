package com.lucasmacedo.pedidos.OrderEvent.validation;

import com.lucasmacedo.pedidos.OrderEvent.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ValidateRequestTest {
    @Autowired
    ValidateRequest validateRequest;

    @Test
    @DisplayName("Deve lançar OrderException quando ID é nulo ou vazio")
    void mustThrowOrderExceptionWhenIdEnuloOrEmpty() {
        assertThrows(OrderException.class, () -> {
            validateRequest.validaRequest(null);
        });

        assertThrows(OrderException.class, () -> {
            validateRequest.validaRequest("");
        });
    }
}
