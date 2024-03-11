package com.backend.leonardo.dto;
import jakarta.validation.constraints.Digits;

public record EfetuarTransacaoDTO(
        @Digits(integer = 10, fraction = 0)
        Double valor,
        char tipo,
        String descricao
) {
}
