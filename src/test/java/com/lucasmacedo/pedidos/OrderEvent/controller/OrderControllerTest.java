package com.lucasmacedo.pedidos.OrderEvent.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmacedo.pedidos.OrderEvent.DTO.Pedidos;
import com.lucasmacedo.pedidos.OrderEvent.fixtures.PedidosTemplate;
import com.lucasmacedo.pedidos.OrderEvent.service.OrdersService;
import com.lucasmacedo.pedidos.OrderEvent.validation.ValidateFields;
import com.lucasmacedo.pedidos.OrderEvent.validation.ValidateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrdersService ordersService;
    @MockBean
    private ValidateRequest validateRequest;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ValidateFields validateFields;

    @Test()
    @DisplayName("Deve Retornar status OK quando um pedido é criado")
    void shouldReturnStatusOkWhenOrderIsCreated() throws Exception {

        Pedidos pedido = PedidosTemplate.pedidos();
        String pedidoJson = objectMapper.writeValueAsString(pedido);

        mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test()
    @DisplayName("Deve Retornar um status OK quando um pedido é atualizado")
    void shouldReturnStatusOkWhenOrderIsUpdated() throws Exception {

        Pedidos pedido = PedidosTemplate.pedidos();
        String pedidoJson = objectMapper.writeValueAsString(pedido);

        mockMvc.perform(MockMvcRequestBuilders.patch("/pedidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test()
    @DisplayName("Deve Retornar um status Method Not Allowed quando o metodo HTTP está incorreto")
    void shouldReturnStatusMethodNotAllowedWhenHttpIsIncorrect() throws Exception {
        Pedidos pedido = PedidosTemplate.pedidos();
        String pedidoJson = objectMapper.writeValueAsString(pedido);

        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoJson))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test()
    @DisplayName("Deve Retornar um status Not Found quando o endpoint está incorreto")
    void shouldReturnStatusNotFoundWhenOrderIsEndpointIncorrect() throws Exception {
        Pedidos pedido = PedidosTemplate.pedidos();
        String pedidoJson = objectMapper.writeValueAsString(pedido);

        mockMvc.perform(MockMvcRequestBuilders.post("/pedidoss")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
