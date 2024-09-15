package com.lucasmacedo.pedidos.OrderEvent.service;

import com.lucasmacedo.pedidos.OrderEvent.DTO.Pedidos;
import com.lucasmacedo.pedidos.OrderEvent.exception.OrderException;
import com.lucasmacedo.pedidos.OrderEvent.fixtures.PedidosTemplate;
import com.lucasmacedo.pedidos.OrderEvent.validation.ValidateFields;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {
    @Mock
    KafkaTemplate<String, Pedidos> kafkaTemplate;

    @Mock
    ValidateFields validateFields;

    @Mock
    SequentialIdGenerator idGenerator;

    @InjectMocks
    OrdersService ordersService;

    @Test
    @DisplayName("Deve enviar pedido com ID gerado e validar interações")
    void MustSendrequestwithidgeneratevalidateinteractions(){
        Pedidos pedido = PedidosTemplate.pedidos();
        long generatedId = 12345L;
        pedido.setIdPedido(String.valueOf(generatedId));

        when(idGenerator.generateNextId()).thenReturn(generatedId);

        CompletableFuture<SendResult<String, Pedidos>> future = new CompletableFuture<>();
        future.complete(new SendResult<>(null, null));

        when(kafkaTemplate.send(anyString(), any(Pedidos.class))).thenReturn(future);

        ordersService.enviar(pedido);

        verify(idGenerator).generateNextId();
        verify(validateFields).validate(pedido);
        verify(kafkaTemplate).send(anyString(), any(Pedidos.class));
    }

    @Test
    @DisplayName("Deve atualizar pedido e validar interações")
    void MustUpdateOrderandValidateInteractions(){
        Pedidos pedidos = PedidosTemplate.pedidos();

        CompletableFuture<SendResult<String, Pedidos>> future = new CompletableFuture<>();
        future.complete(new SendResult<>(null,null));

        when(kafkaTemplate.send(anyString(),any(Pedidos.class))).thenReturn(future);

        ordersService.atualizarPedido(pedidos);

        verify(validateFields).validate(pedidos);
        verify(kafkaTemplate).send(anyString(), any(Pedidos.class));

    }
    @Test
    @DisplayName("Deve tentar atualizar pedido com topico fora e lançar um OrderException")
    void mustUpdateOrderAndLaunchOrderException() {
        Pedidos pedidos = PedidosTemplate.pedidos();

        when(kafkaTemplate.send(anyString(), any(Pedidos.class))).thenThrow(OrderException.class);

        assertThrows(OrderException.class, () -> {
            ordersService.atualizarPedido(pedidos);
        });

        verify(validateFields).validate(pedidos);
        verify(kafkaTemplate).send(anyString(), any(Pedidos.class));
    }

    @Test
    @DisplayName("Deve tentar enviar pedido com topico fora e lançar um OrderException")
    void shouldTrySendOrderAndLaunchOrderException(){
        Pedidos pedidos = PedidosTemplate.pedidos();

        when(kafkaTemplate.send(anyString(),any(Pedidos.class))).thenThrow(OrderException.class);

        assertThrows(OrderException.class, () -> {
            ordersService.enviar(pedidos);
        });

        verify(validateFields).validate(pedidos);
        verify(kafkaTemplate).send(anyString(), any(Pedidos.class));
    }
}
