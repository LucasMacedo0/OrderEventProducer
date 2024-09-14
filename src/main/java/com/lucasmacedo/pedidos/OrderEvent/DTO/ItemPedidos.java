package com.lucasmacedo.pedidos.OrderEvent.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
public class ItemPedidos {

    @NotNull
    @Schema(
            title = "ID do Item",
            description = "Identificador único do item. Deve ser um valor não nulo.",
            example = "item123"
    )
    private String idItem;

    @NotNull
    @Schema(
            title = "Descrição",
            description = "Descrição do item. Deve ser um valor não nulo.",
            example = "Cadeira de escritório"
    )
    private String descricao;

    @NotNull
    @Schema(
            title = "Quantidade",
            description = "Quantidade do item. Deve ser um valor não nulo e deve ser um número inteiro positivo.",
            example = "10"
    )
    private int quantidade;

    @NotNull
    @Schema(
            title = "Preço Unitário",
            description = "Preço unitário do item. Deve ser um valor não nulo e deve ser um número decimal positivo.",
            example = "199.99"
    )
    private BigDecimal precoUnitario;

}
