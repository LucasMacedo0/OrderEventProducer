package com.lucasmacedo.pedidos.OrderEvent.controller;

import com.lucasmacedo.pedidos.OrderEvent.DTO.PedidosDTO;
import com.lucasmacedo.pedidos.OrderEvent.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<PedidosDTO> enviarPedidos(@RequestBody PedidosDTO pedidosDTO) {
         ordersService.enviar(pedidosDTO);
         return new ResponseEntity<>(HttpStatus.OK);
    }


}
