package com.lucasmacedo.pedidos.OrderEvent.controller;

import com.lucasmacedo.pedidos.OrderEvent.DTO.Pedidos;
import com.lucasmacedo.pedidos.OrderEvent.service.OrdersService;
import com.lucasmacedo.pedidos.OrderEvent.validation.ValidateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@ApiResponse()
@Tag(name = "OrderController", description = "Operações relacionadas a pedidos")
public class OrderController {

    @Autowired
    private final OrdersService ordersService;

    @Autowired
    private final ValidateRequest validateRequest;

    @Operation(
            summary = "Cria um novo pedido",
            description = "Cria um novo pedido e o envia para o tópico Kafka."
    )
    @PostMapping("/pedidos")
    public ResponseEntity<Pedidos> enviarPedidos(@RequestBody @NotNull Pedidos pedidos) {
        ordersService.enviar(pedidos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Atualiza um pedido existente",
            description = "Atualiza um pedido e o envia para o tópico Kafka."
    )
    @PatchMapping("/pedidos/{id}")
    public ResponseEntity<Pedidos> atualizarParcialPedido(@PathVariable @NotBlank String id, @RequestBody @NotNull Pedidos pedidos) {
        validateRequest.validaRequest(id);
        ordersService.atualizarPedido(pedidos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
