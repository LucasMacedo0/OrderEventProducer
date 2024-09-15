package com.lucasmacedo.pedidos.OrderEvent.service;

import com.lucasmacedo.pedidos.OrderEvent.DTO.Pedidos;
import com.lucasmacedo.pedidos.OrderEvent.exception.OrderException;
import com.lucasmacedo.pedidos.OrderEvent.validation.ValidateFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {

    @Autowired
    private final KafkaTemplate<String, Pedidos> kafkaTemplate;
    private final String TOPICO_PEDIDOS = "pedidos";

    @Autowired
    private final ValidateFields validateFields;

    @Autowired
    private final SequentialIdGenerator idGenerator;


    public void enviar(Pedidos pedidos) {
        long generatedId = idGenerator.generateNextId();
        pedidos.setIdPedido(String.valueOf(generatedId));
        validateFields.validate(pedidos);
        try {
            pedidos.setACAO("CRIACAO");
            kafkaTemplate.send(TOPICO_PEDIDOS, pedidos);
            log.info("Mensagem enviada ao tópico: {}", TOPICO_PEDIDOS);
        } catch (OrderException e) {
            log.error("Erro ao enviar a mensagem ao tópico {}", TOPICO_PEDIDOS, e);
            throw new OrderException("Erro na comunicação com o Kafka", "Não foi possível enviar a mensagem ao tópico Kafka.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public void atualizarPedido(Pedidos pedidos) {
        validateFields.validate(pedidos);
        try {
            pedidos.setACAO("ATUALIZAR");
            kafkaTemplate.send(TOPICO_PEDIDOS, pedidos);
            log.info("Mensagem de atualização do pedido enviada ao tópico:{}", TOPICO_PEDIDOS);
        } catch (OrderException e) {
            log.error("Erro ao enviar a mensagem ao tópico {}", TOPICO_PEDIDOS, e);
            throw new OrderException("Erro na comunicação com o Kafka", "Não foi possível enviar a mensagem de atualização ao tópico Kafka.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
