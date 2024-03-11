package com.backend.leonardo.dto;

import java.util.List;

public record SaldoETransacoesDTO(RecuperarSaldoDTO saldo, List<RetornoTransacaoCompletaDTO> ultimas_transacoes) {
}
