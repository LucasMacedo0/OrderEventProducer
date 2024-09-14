package com.lucasmacedo.pedidos.OrderEvent.fixtures;

import com.lucasmacedo.pedidos.OrderEvent.DTO.Endereco;
import com.lucasmacedo.pedidos.OrderEvent.DTO.ItemPedidos;
import com.lucasmacedo.pedidos.OrderEvent.DTO.Pedidos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PedidosTemplate {

    public static Pedidos pedidos() {
        return Pedidos.builder().idPedido("1")
                .nomeCliente("nomeCliente")
                .itens(itemList())
                .endereco(endereco())
                .valorTotal(new BigDecimal(10))
                .status("PENDENTE")
                .ACAO("ENVIADO")
                .build();
    }

    public static List<ItemPedidos> itemList() {
        ItemPedidos pedido = ItemPedidos.builder()
                .idItem("1")
                .precoUnitario(new BigDecimal(10))
                .quantidade(1)
                .descricao("descricao")
                .build();

        List<ItemPedidos> pedidos = new ArrayList<>();
        pedidos.add(pedido);

        return pedidos;
    }

    static Endereco endereco() {
        return Endereco.builder().rua("rua")
                .numero("10")
                .complemento("complemento")
                .bairro("bairro")
                .cidade("cidade")
                .estado("estado")
                .cep("cep")
                .build();
    }
}
