package com.backend.leonardo.dto;

import java.time.LocalDateTime;

public record RecuperarSaldoDTO(
                int total,
                LocalDateTime data_extrato,
                int limite) {
}
