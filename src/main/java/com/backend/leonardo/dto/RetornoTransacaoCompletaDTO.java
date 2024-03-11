package com.backend.leonardo.dto;

import java.time.LocalDateTime;

public record RetornoTransacaoCompletaDTO(
        int valor,
        Character tipo,
        String descricao,
        LocalDateTime realizada_em
) {

}
