package com.backend.leonardo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("cliente")
public record Cliente (
        @Id
        @Column("id")
        int id,
        @Column("saldos")
        int valor,
        @Column("limites")
        int limite
) {
}
