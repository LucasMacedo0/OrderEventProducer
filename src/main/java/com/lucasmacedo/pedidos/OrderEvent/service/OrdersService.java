package com.lucasmacedo.pedidos.OrderEvent.service;

import com.lucasmacedo.pedidos.OrderEvent.DTO.PedidosDTO;
import com.lucasmacedo.pedidos.OrderEvent.exception.OrderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {

    @Autowired
    private final KafkaTemplate<String, PedidosDTO> kafkaTemplate;
    private final String TOPICO_PEDIDOS = "pedidos";

    public void enviar(PedidosDTO pedidosDTO) {
        try {
            kafkaTemplate.send("pedidos", pedidosDTO);
            log.info("Mensagem enviada ao tópico {}", TOPICO_PEDIDOS);
        } catch (OrderException e) {
            log.error("Erro ao enviar a mensagem ao tópico {}", TOPICO_PEDIDOS, e);
            throw new OrderException("Erro na comunicação com o Kafka", "Não foi possível enviar a mensagem ao tópico Kafka.");
        }
    }
}
