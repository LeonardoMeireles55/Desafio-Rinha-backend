package com.backend.leonardo.dto;

public record EfetuarTransacaoDTO(
        Double valor,
        char tipo,
        String descricao) {
}
