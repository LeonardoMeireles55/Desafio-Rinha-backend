package com.backend.leonardo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("transacao")
public record Transacao (
        @Id
        int id,
        @Column("id_cliente")
        int clienteFk,
        @Column("valor")
        int valor,
        @Column("tipo")
        char tipo,
        @Column("descricao")
        String descricao,
        @Column("realizada_em")
        LocalDateTime realizadaEm
) {
}