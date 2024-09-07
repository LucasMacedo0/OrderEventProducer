package com.lucasmacedo.pedidos.OrderEvent.service;

import com.lucasmacedo.pedidos.OrderEvent.DTO.PedidosDTO;
import com.lucasmacedo.pedidos.OrderEvent.exception.OrderException;
import com.lucasmacedo.pedidos.OrderEvent.validation.ValidateFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {

    @Autowired
    private final KafkaTemplate<String, PedidosDTO> kafkaTemplate;
    private final String TOPICO_PEDIDOS = "pedidos";

    @Autowired
    private final ValidateFields validateFields;

    @Autowired
    private final SequentialIdGenerator idGenerator;

    public void enviar(PedidosDTO pedidosDTO) {
        long generatedId = idGenerator.generateNextId();
        pedidosDTO.setIdPedido(String.valueOf(generatedId));
        validateFields.validate(pedidosDTO);
        try {
            pedidosDTO.setACAO("CRIACAO");
            kafkaTemplate.send(TOPICO_PEDIDOS, pedidosDTO);
            log.info("Mensagem enviada ao tópico:{}", TOPICO_PEDIDOS);
        } catch (OrderException e) {
            log.error("Erro ao enviar a mensagem ao tópico {}", TOPICO_PEDIDOS, e);
            throw new OrderException("Erro na comunicação com o Kafka", "Não foi possível enviar a mensagem ao tópico Kafka.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public void atualizarPedido(PedidosDTO pedidosDTO) {
        validateFields.validate(pedidosDTO);
        try {
            pedidosDTO.setACAO("ATUALIZAR");
            kafkaTemplate.send(TOPICO_PEDIDOS, pedidosDTO);
            log.info("Mensagem de atualização enviada ao tópico:{}", TOPICO_PEDIDOS);
        } catch (OrderException e) {
            log.error("Erro ao enviar a mensagem ao tópico {}", TOPICO_PEDIDOS, e);
            throw new OrderException("Erro na comunicação com o Kafka", "Não foi possível enviar a mensagem de atualização ao tópico Kafka.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
