package com.lucasmacedo.pedidos.OrderEvent.controller;

import com.lucasmacedo.pedidos.OrderEvent.DTO.PedidosDTO;
import com.lucasmacedo.pedidos.OrderEvent.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ApiResponse()
@Tag(name = "OrderController", description = "Operações relacionadas a pedidos")
public class OrderController {

    private final OrdersService ordersService;

    @Operation(
            summary = "Cria um novo pedido",
            description = "Cria um novo pedido e o envia para o tópico Kafka."
    )
    @PostMapping("/pedidos")
    public ResponseEntity<PedidosDTO> enviarPedidos(@RequestBody PedidosDTO pedidosDTO) {
        ordersService.enviar(pedidosDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
