package com.lucasmacedo.pedidos.OrderEvent.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO {

    @NotNull
    @Schema(
            title = "Rua",
            description = "Nome da rua do endereço. Deve ser um valor não nulo.",
            example = "Rua das Flores"
    )
    private String rua;

    @NotNull
    @Schema(
            title = "Número",
            description = "Número do endereço. Deve ser um valor não nulo.",
            example = "123"
    )
    private String numero;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(
            title = "Complemento",
            description = "Complemento do endereço (por exemplo, apartamento ou bloco). Pode ser vazio.",
            example = "Apto 101"
    )
    private String complemento;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(
            title = "Bairro",
            description = "Bairro do endereço. Pode ser vazio.",
            example = "Jardim das Flores"
    )
    private String bairro;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(
            title = "Cidade",
            description = "Cidade do endereço. Pode ser vazio.",
            example = "São Paulo"
    )
    private String cidade;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(
            title = "Estado",
            description = "Estado do endereço. Pode ser vazio.",
            example = "SP"
    )
    private String estado;

    @NotNull
    @Schema(
            title = "CEP",
            description = "Código Postal (CEP) do endereço. Deve ser um valor não nulo.",
            example = "12345-678"
    )
    private String cep;
}
