package com.backend.leonardo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("transacao")
public class Transacao {
    @Id
    private int id;
    @Column("id_cliente")
    private int clienteFk;
    @Column("valor")
    private int valor;
    @Column("tipo")
    private char tipo;
    @Column("descricao")
    private String descricao;
    @Column("realizada_em")
    private LocalDateTime realizadaEm;

    public Transacao() {
    }

    public int getValor() {
        return valor;
    }

    public char getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public Transacao(int clienteFk, int valor, char tipo, String descricao, LocalDateTime realizadaEm) {
        this.clienteFk = clienteFk;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
    }

    public Transacao(int id, int clienteFk, int valor, char tipo, String descricao, LocalDateTime realizadaEm) {
        this.id = id;
        this.clienteFk = clienteFk;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
    }

}