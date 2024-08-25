package com.lucasmacedo.pedidos.OrderEvent.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PedidosDTO {

    @NotNull
    @Schema(
            title = "ID do Pedido",
            description = "Identificador único do pedido. Deve ser um valor não nulo.",
            example = "12345"
    )
    private String idPedido;

    @NotNull
    @Schema(
            title = "ID do Cliente",
            description = "Identificador único do cliente associado ao pedido. Deve ser um valor não nulo.",
            example = "cliente-123"
    )
    private String idCliente;

    @NotNull
    @NotNull
    @Size(min = 1)
    @Schema(
            title = "Itens do Pedido",
            description = "Lista de itens incluídos no pedido. Deve conter pelo menos um item."
    )
    private List<ItemDTO> itens;

    @NotNull
    @Valid
    @Schema(
            title = "Endereço de Entrega",
            description = "Informações sobre o endereço de entrega do pedido. Deve ser um valor não nulo."
    )
    private EnderecoDTO endereco;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(
            title = "Valor Total",
            description = "Valor total do pedido. Deve ser um número decimal positivo.",
            example = "99.99"
    )
    private BigDecimal valorTotal;

    @Pattern(regexp = "PENDENTE|PROCESSANDO|CONCLUÍDO")
    @Schema(
            title = "Status do Pedido",
            description = "Status atual do pedido. Os valores possíveis são 'PENDENTE', 'PROCESSANDO', ou 'CONCLUÍDO'.",
            example = "PENDENTE"
    )
    private String status = "PENDENTE";

}
