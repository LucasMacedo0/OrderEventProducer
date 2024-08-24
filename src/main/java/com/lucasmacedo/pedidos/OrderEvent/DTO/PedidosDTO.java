package com.lucasmacedo.pedidos.OrderEvent.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidosDTO {

    private String idPedido;
    private String idCliente;
    private List<ItemDTO> itens;
    private EnderecoDTO endereco;
    private BigDecimal valorTotal;
    private String status = "PENDENTE";

}
