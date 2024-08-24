package com.lucasmacedo.pedidos.OrderEvent.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {

    private String idItem;
    private String descricao;
    private int quantidade;
    private BigDecimal precoUnitario;


}
