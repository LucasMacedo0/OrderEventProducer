package com.lucasmacedo.pedidos.OrderEvent.validation;

import com.lucasmacedo.pedidos.OrderEvent.exception.OrderException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ValidateFieldsTest {

    private final Validator validator = mock(Validator.class);
    private final ValidateFields validateFields = new ValidateFields(validator);

    @Test
    @DisplayName("Deve lançar OrderException quando há violações de validação")
    void mustThrowOrderExceptionWhenThereHaViolacoesDeValidacao() {
        ConstraintViolation<Object> violation = mock(ConstraintViolation.class);
        Path path = mock(Path.class);

        when(path.toString()).thenReturn("campo");
        when(violation.getPropertyPath()).thenReturn(path);
        when(violation.getMessage()).thenReturn("mensagem de erro");

        Set<ConstraintViolation<Object>> violations = Collections.singleton(violation);
        when(validator.validate(any())).thenReturn(violations);

        assertThrows(OrderException.class, () -> {
            validateFields.validate(new Object());
        });
    }

    @Test
    @DisplayName("Não deve lançar exceção quando não há violações de validação")
    void shouldNotThrowExceptionWhenNoViolations() {
        when(validator.validate(any())).thenReturn(Collections.emptySet());

        validateFields.validate(new Object());
    }
}
